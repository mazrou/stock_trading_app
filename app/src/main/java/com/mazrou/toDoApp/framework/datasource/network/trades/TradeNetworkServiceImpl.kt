package com.mazrou.toDoApp.framework.datasource.network.trades

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.models.TradingType
import com.mazrou.toDoApp.framework.presentation.util.TAG
import com.mazrou.toDoApp.framework.utils.cLog
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.streams.toList

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

    override suspend fun makeTrade(trade: Trade): Boolean {
        var success = false
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
            }.addOnSuccessListener() {
                success = true
            }.await()

        if (success) {
            val balance = getBalance()
            if (trade.type == TradingType.BUY) {
                setBalance(balance - (trade.price * trade.quantity))
            } else {
                setBalance(balance + (trade.price * trade.quantity))
            }
        }
        return success
    }

    override suspend fun getTradeHistory(): List<Trade> {
        return fireStore
            .collection("Trades")
            .document(deviceID)
            .collection("TradeHistory")
            .get()
            .await()
            .documents
            .stream()
            .map {
                it.data?.let { item ->
                    Trade(
                        price = item["price"] as Double,
                        quantity = (item["quantity"] as Long).toInt(),
                        ticker = item["ticker"] as String,
                        type = TradingType.valueOf(item["type"] as String),
                        date = LocalDateTime.parse(
                            it.id,
                            DateTimeFormatter.ISO_DATE_TIME
                        )
                    )
                }
            }.toList()
    }


}