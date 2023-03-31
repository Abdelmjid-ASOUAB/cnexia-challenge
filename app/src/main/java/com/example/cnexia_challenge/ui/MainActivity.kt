package com.example.cnexia_challenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cnexia_challenge.databinding.ActivityMainBinding
import com.example.cnexia_challenge.ui.adapters.CarsAdapter
import com.example.cnexia_challenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var carsAdapter: CarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.initRecyclerView()
        this.loadCars()
        this.observerCarsResponse()
    }

    /**
     * Load cars from
     */
    private fun loadCars() {
        this.viewModel.loadCarsList()
    }

    /**
     * Observe cars response
     */
    private fun observerCarsResponse() {
        this.viewModel.getCarListLiveData().observe(this) { cars ->
            this.carsAdapter.setCarsList(cars)
        }
    }

    /**
     * Init recycler view and set adapter
     */
    private fun initRecyclerView() {
        this.binding.listCars.setHasFixedSize(true)
        this.binding.listCars.layoutManager = LinearLayoutManager(applicationContext)
        this.binding.listCars.adapter = this.carsAdapter
    }
}