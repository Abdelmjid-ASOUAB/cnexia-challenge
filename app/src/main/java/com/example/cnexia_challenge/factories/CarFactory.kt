package com.example.cnexia_challenge.factories

import com.example.cnexia_challenge.models.Car
import com.google.gson.Gson
import org.json.JSONArray

/**
 *  CarFactory
 *  Use to convert json to Car or List<Car>
 */
object CarFactory{
    /**
     *  Convert json to Car
     *  @param json a String json object
     *
     *  @return Car
     */
    private fun jsonToCar(json: String): Car {
        return Gson().fromJson(json, Car::class.java);
    }

    /**
     *  Convert json to List<Car>
     *  @param json a String json array
     *
     *  @return List<Car>
     */
    fun jsonToListCar(json: String): List<Car> {
        val listCar = ArrayList<Car>()
        val arrayJson = JSONArray(json)

        for (i in 0 until arrayJson.length()) {
            val car = jsonToCar(arrayJson[i].toString())

            listCar.add(car)
        }

        return listCar
    }
}
