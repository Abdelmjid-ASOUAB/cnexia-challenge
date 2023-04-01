package com.example.cnexia_challenge.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cnexia_challenge.networking.FakeNetworking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * This class is used to populate the database with data when the database is created.
 */
class OnCreateCallback @Inject constructor(
    private val fakeNetworking: FakeNetworking,
    private val carDao: Provider<CarDao>,
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        GlobalScope.launch {
            val cars = fakeNetworking.loadListCars()
            carDao.get().insertCars(cars)
        }
    }
}
