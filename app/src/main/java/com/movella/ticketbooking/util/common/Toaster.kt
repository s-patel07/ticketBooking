package com.movella.ticketbooking.util.common

import android.content.Context
import android.widget.Toast

object Toaster {
    fun show(context: Context, text: CharSequence) {
        val toast =  Toast.makeText(context, text, Toast.LENGTH_SHORT)
//        toast.view.background.setColorFilter(
//            ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN
//        )
//        val textView = toast.view.findViewById(android.R.id.message) as TextView
//        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        toast.show()
    }

}