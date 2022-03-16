package com.movella.ticketbooking.di.component

import com.movella.ticketbooking.di.ActivityScope
import com.movella.ticketbooking.di.module.ActivityModule
import com.movella.ticketbooking.ui.booking.BookingActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(bookingActivity: BookingActivity)

}