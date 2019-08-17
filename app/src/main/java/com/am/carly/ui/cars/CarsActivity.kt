package com.am.carly.ui.cars

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.am.carly.R
import com.am.carly.data.model.Car
import com.am.carly.databinding.ActivityCarsBinding
import com.am.carly.databinding.ItemCarBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.util.PARM_CAR_MODEL
import com.am.carly.util.PARM_CITY_CODE
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cars.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CarsActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CarsViewModelFactory by instance()
    private lateinit var mBinding: ActivityCarsBinding
    private lateinit var mViewModel: CarsViewModel
    private lateinit var mCityName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        mCityName = extras.getString(PARM_CITY_CODE).toLowerCase()

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cars)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CarsViewModel::class.java)
        mBinding.viewModel = mViewModel
        loadCarsFromFireStore()

    }

    private fun loadCarsFromFireStore() {
        val query = FirebaseFirestore.getInstance().collection("cars_$mCityName")
        val options = FirestoreRecyclerOptions.Builder<Car>()
            .setQuery(query, Car::class.java)
            .setLifecycleOwner(this@CarsActivity)
            .build()
        val adapter = object : FirestoreRecyclerAdapter<Car, CarHolder>(options) {
            override fun onBindViewHolder(holder: CarHolder, position: Int, model: Car) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding = DataBindingUtil.inflate<ItemCarBinding>(inflater, R.layout.item_car, parent, false)
                return CarHolder(binding)
            }
        }
        carsRecyclerView.adapter = adapter
        carsRecyclerView.layoutManager = LinearLayoutManager(this@CarsActivity)
    }

    inner class CarHolder(var binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var mCar: Car

        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(car: Car) {
            mCar = car
            if (car.imagesList!!.isNotEmpty()) {
                Glide.with(binding.root.context).load(car.imagesList!![0]).into(binding.carImageView)
            }
            binding.nameTextView.text = car.name
            binding.ratingBar.progress = car.rating!!
            binding.pricePerDayTextView.text = "${car.pricePerDay} SIN/day"
        }

        override fun onClick(v: View?) {
            startActivity(Intent(this@CarsActivity, CarDetailsActivity::class.java).also {
                it.putExtra(PARM_CAR_MODEL, mCar)
            })
        }
    }
}
