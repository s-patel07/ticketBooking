package com.movella.ticketbooking.data.local.dao

import androidx.room.*
import com.movella.ticketbooking.data.local.entity.Seat

@Dao
interface TicketBookingDao {

    @Query("SELECT * FROM bus_seat")
    fun getAllBusSeats(): List<Seat>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusData(seatList: List<Seat>)

    @Delete
    fun deleteBusSeat(seat: Seat?)

    @Update
    fun updateBusSeat(seat: Seat?)

}