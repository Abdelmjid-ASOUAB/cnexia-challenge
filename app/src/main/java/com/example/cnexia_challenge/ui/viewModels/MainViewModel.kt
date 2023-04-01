package com.example.cnexia_challenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.repositories.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    private var listCarLiveData: MutableLiveData<List<Car>> = MutableLiveData()

    init {
        loadCarsList()
    }

    /**
     * Load the list of cars
     * from the network
     */
    private fun loadCarsList() {
        this.repository.getAllCars().observeForever {
            listCarLiveData.postValue(it)
        }
    }

    /**
     * Filter the list of cars
     * by make
     */
    fun filterCarsByMake(make: String) {
        this.repository.filterCarsByMake(make).observeForever {
            this.listCarLiveData.postValue(it)
        }

    }

    /**
     * Filter the list of cars
     * by model
     */
    fun filterCarsByModel(model: String) {
        this.repository.filterCarsByModel(model).observeForever {
            this.listCarLiveData.postValue(it)
        }
    }

    /**
     * Get the list of cars
     * for the view
     */
    val cars: LiveData<List<Car>>
        get() = listCarLiveData

}
