package com.movella.ticketbooking

import android.app.Application
import com.movella.ticketbooking.di.component.ApplicationComponent
import com.movella.ticketbooking.di.component.DaggerApplicationComponent
import com.movella.ticketbooking.di.module.ApplicationModule

class BookingApp :Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}