package com.mazrou.toDoApp.framework.datasource.network.trades

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.framework.presentation.util.TAG
import com.mazrou.toDoApp.framework.utils.cLog
import kotlinx.coroutines.tasks.await

class TradeNetworkServiceImpl(
    private val fireStore: FirebaseFirestore,
    context: Context
) : TradeNetworkService {


    @SuppressLint("HardwareIds")
    val deviceID: String = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )

    override suspend fun getBalance(): Double {

        return fireStore
            .collection("Trades")
            .document(deviceID)
            .get()
            .addOnFailureListener {
                // send error reports to Firebase Crashlytics
                cLog(it.message)
            }.await()
            .data?.let {
                return try {
                    it["balance"] as Double
                } catch (e: ClassCastException) {
                    (it["balance"] as Long).toDouble()
                }
            } ?: kotlin.run {
            setBalance(10000.0)
            return getBalance()
        }
    }

    override suspend fun setBalance(balance: Double) {
        val map = mutableMapOf<String, Double>()
        map["balance"] = balance
        fireStore
            .collection("Trades")
            .document(deviceID)
            .set(map)
            .addOnFailureListener {
                // send error reports to Firebase Crashlytics
                cLog(it.message)
            }.await()
    }

    override suspend fun buyStock(trade: Trade): Boolean {
        var sucess = false
        fireStore
            .collection("Trades")
            .document(deviceID)
            .collection("TradeHistory")
            .document(trade.date.toString())
            .set(trade.toTradeDto())
            .addOnFailureListener {
                // send error reports to Firebase Crashlytics
                Log.e(TAG, "Error accrued on sending data to fireStore : ${it.message}")
                cLog(it.message)
            }.addOnSuccessListener {
                sucess = true
            }.await()
        return sucess
    }

}