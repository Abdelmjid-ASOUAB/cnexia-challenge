package com.example.cnexia_challenge.ui.viewModels

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

    /**
     * Load the list of cars
     * from the network
     */
    fun loadCarsList() {
        this.listCarLiveData.postValue(fakeNetworking.loadListCars())
    }

    /**
     * Get the list of cars
     * for the view
     */
    fun getCarListLiveData(): LiveData<List<Car>> {
        return this.listCarLiveData
    }
}
