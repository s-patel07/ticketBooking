package com.movella.ticketbooking.ui.booking

import androidx.lifecycle.MutableLiveData
import com.movella.ticketbooking.data.local.LocalRepository
import com.movella.ticketbooking.data.local.entity.Seat
import com.movella.ticketbooking.ui.base.BaseViewModel
import com.movella.ticketbooking.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class BookingViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val localRepository: LocalRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    override fun onCreate() {

    }

    var seatData: MutableLiveData<List<Seat>> = MutableLiveData()


    private fun getRecords() {
        val list = localRepository.getRecords()
        seatData.postValue(list)
    }

    fun insertBusSeats() {
        localRepository.insertRecords()
        getRecords()
    }

    fun updateBusSeat(seat: Seat) {
        localRepository.updateRecords(seat)
    }

    fun setReminder(seat: Seat, calendar: Calendar) {
        localRepository.setReminder(seat, calendar)
    }

}