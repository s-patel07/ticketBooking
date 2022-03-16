package  com.movella.ticketbooking.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus_seat")
data class Seat(
    @PrimaryKey(autoGenerate = false)  val id : String,
    var seatStatus: String,
    var dateOfBooking : String,
    var timeOfBooking:String,
    var remindBefore:String)
