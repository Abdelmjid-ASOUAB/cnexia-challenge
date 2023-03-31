package com.example.cnexia_challenge.networking

import android.content.Context
import com.example.cnexia_challenge.R
import com.example.cnexia_challenge.factories.CarFactory.jsonToListCar
import com.example.models.Car
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FakeNetworking @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    /**
     *  Load list of cars from json file
     *
     *  @return List<Car>
     */
    fun loadListCars(): List<Car> {
        val jsonText = context.resources.openRawResource(R.raw.car_list)
            .bufferedReader().use { it.readText() }

        return jsonToListCar(jsonText)
    }
}
