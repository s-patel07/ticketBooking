package com.movella.ticketbooking.data.local

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.movella.ticketbooking.data.local.dao.TicketBookingDao
import com.movella.ticketbooking.data.local.entity.Seat
import com.movella.ticketbooking.ui.booking.NotificationReceiver
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LocalRepository @Inject constructor(
    private val appDao: TicketBookingDao,
    private val context: Context
) {

    fun getRecords(): List<Seat>? {
        return appDao.getAllBusSeats()
    }

    fun updateRecords(seat: Seat) {
        return appDao.updateBusSeat(seat)
    }

    fun insertRecords() {
        val seatsList = ArrayList<Seat>()
        for (index in 1..15) {
            val seatEntity = Seat(index.toString(), "1", "", "", "")
            seatsList.add(seatEntity)
        }
        appDao.insertBusData(seatsList)
    }


    fun setReminder(it: Seat, calendar: Calendar) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("bus_date", it.dateOfBooking)
        intent.putExtra("bus_time", it.timeOfBooking)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 1000,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(
            context,
            "Booking Successful, Will Remind you before ${it.remindBefore} minute",
            Toast.LENGTH_SHORT
        ).show();
    }
}