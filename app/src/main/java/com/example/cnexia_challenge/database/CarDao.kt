package com.example.cnexia_challenge.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cnexia_challenge.models.Car

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(cars: List<Car>)

    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: String): Car

    @Query("SELECT * FROM cars WHERE make Like '%' || :make || '%'")
    fun getCarByMake(make: String): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE model Like '%' || :model || '%'")
    fun getCarByModel(model: String): LiveData<List<Car>>
}
