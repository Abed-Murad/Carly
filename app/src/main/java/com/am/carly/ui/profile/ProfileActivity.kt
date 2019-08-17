package com.am.carly.ui.profile

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
import com.am.carly.databinding.ActivityProfileBinding
import com.am.carly.databinding.ItemCarBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.cars.CarDetailsActivity
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cars.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class ProfileActivity : BaseActivity(), KodeinAware {

    override val kodein by kodein()
    private val mFactory: ProfileViewModelFactory by instance()
    private lateinit var mBinding: ActivityProfileBinding
    private lateinit var mViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        mViewModel = ViewModelProviders.of(this, mFactory).get(ProfileViewModel::class.java)
        mBinding.viewModel = mViewModel
        populateUserDetails()
        loadCarsFromFireStore()

    }

    private fun populateUserDetails() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            mBinding.userNameTextView.text = name
            mBinding.userEmailTextView.text = email

            Glide.with(this@ProfileActivity).load(photoUrl).into(mBinding.userImageView)
        }
    }


    private fun loadCarsFromFireStore() {
        val query = FirebaseFirestore.getInstance().collection("cars_gaza")
        val options = FirestoreRecyclerOptions.Builder<Car>()
            .setQuery(query, Car::class.java)
            .setLifecycleOwner(this@ProfileActivity)
            .build()
        val adapter = object : FirestoreRecyclerAdapter<Car, CarHolder>(options) {
            override fun onBindViewHolder(holder: CarHolder, position: Int, model: Car) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding =
                    DataBindingUtil.inflate<ItemCarBinding>(inflater, R.layout.item_car, parent, false)
                return CarHolder(binding)
            }
        }
        carsRecyclerView.adapter = adapter
        carsRecyclerView.layoutManager = LinearLayoutManager(this@ProfileActivity)
    }


    inner class CarHolder(var binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(car: Car) {
            if (car.imagesList != null) {
                Glide.with(binding.root.context).load(car.imagesList!![0]).into(binding.carImageView)
            }
            binding.nameTextView.text = car.name
            binding.ratingBar.progress = car.rating!!
            binding.pricePerDayTextView.text = "${car.pricePerDay} SIN/day"
        }

        override fun onClick(v: View?) {
            startActivity(Intent(this@ProfileActivity, CarDetailsActivity::class.java))
        }
    }


}