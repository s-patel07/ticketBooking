package  com.movella.ticketbooking.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movella.ticketbooking.data.local.dao.TicketBookingDao
import com.movella.ticketbooking.data.local.entity.Seat

@Database(entities = [Seat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBusSeatDAO(): TicketBookingDao
    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext, AppDatabase::class.java, "TicketBooking")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}