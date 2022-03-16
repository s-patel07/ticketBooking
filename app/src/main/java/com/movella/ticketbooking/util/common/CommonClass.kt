package com.movella.ticketbooking.util.common

import android.text.TextUtils
import android.util.Patterns

object CommonClass {

    fun getScreenDimens(double: Double) : String {
        return when {
            double <= 5.2 -> {
                "_9_16"
            }
            double > 5.2 && double < 7.0 -> {
                "_9_19_5"
            }
            double <= 7.0 -> {
                "_3_4"
            }
            else -> {
                "_9_19_5"
            }
        }
    }

    fun isVaildEmail(email: String) : Boolean {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

}