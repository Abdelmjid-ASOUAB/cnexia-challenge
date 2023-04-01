package com.example.cnexia_challenge.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.networking.FakeNetworking
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fakeNetworking: FakeNetworking
) : ViewModel() {

    private val listCarLiveData: MutableLiveData<List<Car>> = MutableLiveData()

    init {
        loadCarsList()
    }

    /**
     * Load the list of cars
     * from the network
     */
    fun loadCarsList() {
        this.listCarLiveData.postValue(fakeNetworking.loadListCars())
    }

    /**
     * Filter the list of cars
     * by make
     */
    fun filterCarsByMake(make: String) {
        //TODO implement the filter after implementing room database
        Log.e("TAG_FILTER", "filterCarsByMake: $make" )
    }

    /**
     * Filter the list of cars
     * by model
     */
    fun filterCarsByModel(model: String) {
        //TODO implement the filter after implementing room database
        Log.e("TAG_FILTER", "filterCarsByModel: $model" )
    }

    /**
     * Get the list of cars
     * for the view
     */
    fun getCarListLiveData(): LiveData<List<Car>> {
        return this.listCarLiveData
    }
}
