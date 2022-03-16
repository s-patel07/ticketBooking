package com.movella.ticketbooking.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movella.ticketbooking.util.common.Resource
import com.movella.ticketbooking.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel (
    protected val schedulerProvider: SchedulerProvider,
    protected var compositeDisposable: CompositeDisposable,
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun responseFailureError(error: String) {
        messageString.postValue(Resource.error(error))
    }

    abstract fun onCreate()
}