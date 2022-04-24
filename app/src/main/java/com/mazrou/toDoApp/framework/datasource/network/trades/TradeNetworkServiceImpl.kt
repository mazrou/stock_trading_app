package com.mazrou.toDoApp.framework.datasource.network.trades

import com.google.firebase.firestore.FirebaseFirestore
import com.mazrou.toDoApp.framework.utils.cLog
import kotlinx.coroutines.tasks.await

class TradeNetworkServiceImpl(
    private val fireStore: FirebaseFirestore
) : TradeNetworkService {


    override suspend fun getBalance(): Double {

        return fireStore
            .collection("NOTES_COLLECTION")
            .document("USER_ID")
            .collection("NOTES_COLLECTION")
            .get()
            .addOnFailureListener {
                // send error reports to Firebase Crashlytics
                cLog(it.message)
            }.await()
            .toObjects(Double::class.java)
            .first()
    }

    override suspend fun setBalance(balance: Double) {

    }
}