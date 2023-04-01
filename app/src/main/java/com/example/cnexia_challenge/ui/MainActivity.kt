package com.example.cnexia_challenge.ui

import android.R as AndroidR
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cnexia_challenge.databinding.ActivityMainBinding
import com.example.cnexia_challenge.factories.CarFactory.getMakes
import com.example.cnexia_challenge.factories.CarFactory.getModels
import com.example.cnexia_challenge.ui.adapters.CarsAdapter
import com.example.cnexia_challenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.cnexia_challenge.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var filterModelAdapter: ArrayAdapter<String>
    private lateinit var filterMakeAdapter: ArrayAdapter<String>

    @Inject
    lateinit var carsAdapter: CarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.initRecyclerView()
        this.loadCars()
        this.observerCarsResponse()
        this.initModelFiltersSpinner()
        this.initMakeFiltersSpinner()
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
            this.filterModelAdapter.addAll(getModels(cars))
            this.filterMakeAdapter.addAll(getMakes(cars))
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

    /**
     * Init model filters spinner
     */
    private fun initModelFiltersSpinner() {
        this.filterModelAdapter = ArrayAdapter(this, AndroidR.layout.simple_spinner_item, mutableListOf<String>())
        this.filterModelAdapter.setDropDownViewResource(AndroidR.layout.simple_spinner_dropdown_item)
        this.binding.filter.modelFiler.spinner.adapter = this.filterModelAdapter
        this.filterModelAdapter.add(getString(R.string.filter_by_model))
    }

    /**
     * Init make filters spinner
     */
    private fun initMakeFiltersSpinner() {
        this.filterMakeAdapter = ArrayAdapter(this, AndroidR.layout.simple_spinner_item, mutableListOf<String>())
        this.filterMakeAdapter.setDropDownViewResource(AndroidR.layout.simple_spinner_dropdown_item)
        this.binding.filter.makeFiler.spinner.adapter = this.filterMakeAdapter
        this.filterMakeAdapter.add(getString(R.string.filter_by_make))
    }
}