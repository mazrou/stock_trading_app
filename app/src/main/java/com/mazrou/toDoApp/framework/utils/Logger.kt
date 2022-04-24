package com.mazrou.toDoApp.framework.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mazrou.toDoApp.BuildConfig.DEBUG

fun cLog(msg: String?){
    msg?.let {
        if(!DEBUG){
            FirebaseCrashlytics.getInstance().log(it)
        }
    }

}