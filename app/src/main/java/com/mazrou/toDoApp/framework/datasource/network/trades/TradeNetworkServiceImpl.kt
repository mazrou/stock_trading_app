package com.mazrou.toDoApp.framework.datasource.network.trades

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
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

       return  fireStore
            .collection("Trades")
            .document("dsiodfijsdf")
            .get()
            .addOnFailureListener {
                // send error reports to Firebase Crashlytics
                cLog(it.message)
            }.await()
             .data?.let {
               return try {
                   it["balance"]  as Double
               }catch (e : ClassCastException){
                   (it["balance"] as Long).toDouble()
               }
           }?: kotlin.run {return 0.0}
    }

    override suspend fun setBalance(balance: Double) {

    }

}