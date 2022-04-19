package com.mazrou.toDoApp.business.domain.uitils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object{

        private val TAG: String = "AppDebug"

        // dates from server look like this: "2019-07-23T03:28:01.406944Z"
        fun convertServerStringDateToLong(sd: String): Long{
            val stringDate = sd.removeRange(sd.indexOf("T") until sd.length)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            try {
                sdf.parse(stringDate)?.let {
                    return it.time
                }?: kotlin.run {
                    throw NullPointerException()
                }
            } catch (e: Exception) {
                throw Exception(e)
            }
        }

        fun convertLongToStringDate(longDate: Long): String{
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            try {
                val date = sdf.format(Date(longDate))
                return date
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }


}