package com.movella.ticketbooking.di.component

import android.app.Application
import com.movella.ticketbooking.BookingApp
import com.movella.ticketbooking.data.local.LocalRepository
import com.movella.ticketbooking.di.module.ApplicationModule
import com.movella.ticketbooking.util.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: BookingApp)

    fun getApplication(): Application


    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getLocalRepository(): LocalRepository

}