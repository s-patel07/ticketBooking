package com.movella.ticketbooking.di.module

import android.app.Application
import com.movella.ticketbooking.BookingApp
import com.movella.ticketbooking.data.local.AppDatabase
import com.movella.ticketbooking.data.local.LocalRepository
import com.movella.ticketbooking.util.rx.RxSchedulerProvider
import com.movella.ticketbooking.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BookingApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

  /*  @Provides
    @Singleton
    fun provideAppDataBase(): AppDatabase {
        return
    }
*/

   /* @Singleton
    @Provides
    fun provideDao(appDB: AppDatabase): TicketBookingDao {
        return appDB.getBusSeatDAO()
    }*/
    /*


     @Singleton
     @Provides
     fun provideLocalRepository(appDao: TicketBookingDao): LocalRepository {
         return LocalRepository(appDao,application)
     }*/


    @Singleton
    @Provides
    fun provideLocalRepository(): LocalRepository {
        return LocalRepository(AppDatabase.getAppDB(application).getBusSeatDAO(),application)
    }
}