package com.example.cnexia_challenge.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.models.MakeModelFilter
import com.example.cnexia_challenge.repositories.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    private var listCarLiveData: MutableLiveData<List<Car>> = MutableLiveData()
    private var makeModelFilter: MakeModelFilter = MakeModelFilter()

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
     *
     * @param make the make to filter by
     */
    private fun filterCarsByMake(make: String) {
        this.repository.filterCarsByMake(make).observeForever {
            this.listCarLiveData.postValue(it)
        }

    }

    /**
     * Filter the list of cars
     * by model
     *
     * @param model the model to filter by
     */
    private fun filterCarsByModel(model: String) {
        this.repository.filterCarsByModel(model).observeForever {
            this.listCarLiveData.postValue(it)
        }
    }

    /**
     * Filter the list of cars
     * by model and make
     *
     * @param filter the filter to apply
     */
    fun filterCarsByMakeAndModel(filter: MakeModelFilter) {
        this.filter = filter

        if (this.filter.make.isNotEmpty() && this.filter.model.isNotEmpty()) {
            this.repository.filterCarsByMakeAndModel(this.filter).observeForever {
                this.listCarLiveData.postValue(it)
            }
        } else if (this.filter.make.isNotEmpty()) {
            this.filterCarsByMake(this.filter.make)
        } else if (this.filter.model.isNotEmpty()) {
            this.filterCarsByModel(this.filter.model)
        } else {
            this.loadCarsList()
        }
    }

    /**
     * Get the list of cars
     * for the view
     */
    val cars: LiveData<List<Car>>
        get() = listCarLiveData

    /**
     * Getter for the filter
     */
    var filter: MakeModelFilter
        get() = makeModelFilter
        set(value) {
            makeModelFilter = value
        }
}
