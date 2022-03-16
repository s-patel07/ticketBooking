package com.movella.ticketbooking.util.common

object Constants {

    const val MODEL_WIDTH = 480
    const val MODEL_HEIGHT = 640

    enum class AVAIL(val value: String) {
        SEAT_AVAILABLE("0"),
        SEAT_SELECTED("1"),
        SEAT_NOT_AVAILABLE("2"),
    }

}