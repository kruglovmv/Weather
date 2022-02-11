package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeather() = getDataFromLocalSource()

    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            val rnds = (0..2).random()
            when(rnds){
            0->liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
            1->liveDataToObserve.postValue(AppState.Error(Throwable("Произошла ошибка загрузки!!")))
            2->liveDataToObserve.postValue(AppState.Loading)
            }
        }.start()
    }
}