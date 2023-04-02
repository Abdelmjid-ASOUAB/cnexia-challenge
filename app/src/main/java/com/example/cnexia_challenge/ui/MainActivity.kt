package com.example.cnexia_challenge.ui

import android.R as AndroidR
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
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
import com.example.cnexia_challenge.models.Car

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var carsAdapter: CarsAdapter
    private lateinit var filterModelAdapter: ArrayAdapter<String>
    private lateinit var filterMakeAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.initRecyclerView()
        this.observerCarsResponse()
        this.initModelFiltersSpinner()
        this.initMakeFiltersSpinner()
        this.setMakeFilerListener()
        this.setModelFilerListener()
    }

    /**
     * Observe cars response
     */
    private fun observerCarsResponse() {
        this.viewModel.cars.observe(this) { cars ->
            this.carsAdapter.setCarsList(cars)
            this.addFilterSpinnerData(cars)
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
    }

    /**
     * Init make filters spinner
     */
    private fun initMakeFiltersSpinner() {
        this.filterMakeAdapter = ArrayAdapter(this, AndroidR.layout.simple_spinner_item, mutableListOf<String>())
        this.filterMakeAdapter.setDropDownViewResource(AndroidR.layout.simple_spinner_dropdown_item)
        this.binding.filter.makeFiler.spinner.adapter = this.filterMakeAdapter
    }

    /**
     * Add filter spinner data
     */
    private fun addFilterSpinnerData(cars: List<Car>) {
        if (cars.isEmpty() || this.filterModelAdapter.count != 0 && this.filterMakeAdapter.count != 0) {
            return
        }


        this.filterModelAdapter.add(getString(R.string.filter_by_model))
        this.filterMakeAdapter.add(getString(R.string.filter_by_make))

        this.filterModelAdapter.addAll(getModels(cars))
        this.filterMakeAdapter.addAll(getMakes(cars))
    }

    /**
     * Set model filter listener
     */
    private fun setModelFilerListener() {
        this.binding.filter.modelFiler.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val model = if (position == 0) {
                    ""
                } else {
                    parent?.getItemAtPosition(position) as String
                }

                val filter = viewModel.filter.copy(
                    model = model
                )

                viewModel.filterCarsByMakeAndModel(filter)
            }
        }

    }

    /**
     * Set make filter listener
     */
    private fun setMakeFilerListener() {
        this.binding.filter.makeFiler.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val make = if (position == 0) {
                    ""
                } else {
                    parent?.getItemAtPosition(position) as String
                }


                val filter = viewModel.filter.copy(
                    make = make
                )

                viewModel.filterCarsByMakeAndModel(filter)
            }
        }

    }
}
