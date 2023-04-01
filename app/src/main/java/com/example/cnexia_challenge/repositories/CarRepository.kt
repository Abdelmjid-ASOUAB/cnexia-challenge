package com.example.cnexia_challenge.repositories

import androidx.lifecycle.LiveData
import com.example.cnexia_challenge.database.CarDao
import com.example.cnexia_challenge.models.Car
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepository @Inject constructor(
    private val carDao: CarDao,
) {
    private val listCarLiveData: LiveData<List<Car>> = carDao.getAllCars()

    fun getAllCars(): LiveData<List<Car>> {
        return this.listCarLiveData
    }

    fun filterCarsByMake(make: String): LiveData<List<Car>> {
        return this.carDao.getCarByMake(make)
    }

    fun filterCarsByModel(model: String): LiveData<List<Car>> {
        return this.carDao.getCarByModel(model)
    }
}
