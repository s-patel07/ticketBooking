package com.movella.ticketbooking.di.module

import androidx.lifecycle.ViewModelProvider
import com.movella.ticketbooking.data.local.LocalRepository
import com.movella.ticketbooking.ui.base.BaseActivity
import com.movella.ticketbooking.ui.booking.BookingViewModel
import com.movella.ticketbooking.util.common.ViewModelProviderFactory
import com.movella.ticketbooking.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideBookingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        localRepository: LocalRepository
    ): BookingViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(BookingViewModel::class) {
            BookingViewModel(
                schedulerProvider,
                compositeDisposable,
                localRepository
            )
        }).get(BookingViewModel::class.java)


}