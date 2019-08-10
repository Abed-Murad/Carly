package com.am.carly.ui.cities

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
import com.am.carly.data.model.City
import com.am.carly.databinding.ActivityCitiesBinding
import com.am.carly.databinding.ItemCityBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.base.viewmodelfactory.CitiesViewModelFactory
import com.am.carly.ui.cars.CarsActivity
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cities.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class CitiesActivity : BaseActivity(), KodeinAware {
    override val kodein by kodein()
    private val mFactory: CitiesViewModelFactory by instance()
    private lateinit var mBinding: ActivityCitiesBinding
    private lateinit var mViewModel: CitiesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cities)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CitiesViewModel::class.java)

        val query = FirebaseFirestore.getInstance().collection("cities")


        val options = FirestoreRecyclerOptions.Builder<City>().setQuery(query, City::class.java)
            .setLifecycleOwner(this@CitiesActivity).build()


        val adapter = object : FirestoreRecyclerAdapter<City, CityHolder>(options) {
            override fun onBindViewHolder(holder: CityHolder, position: Int, model: City) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {

                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding = DataBindingUtil.inflate<ItemCityBinding>(inflater, R.layout.item_city, parent, false)

                return CityHolder(binding)
            }
        }
        adapter.startListening()
        citiesRecyclerView.adapter = adapter
        citiesRecyclerView.layoutManager = LinearLayoutManager(this@CitiesActivity)
    }

   inner class CityHolder(var binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(city: City) {
            Glide.with(binding.root.context).load(city.imgPath).into(binding.cityImageView)
            binding.cityNameTextView.text = city.name
        }

        override fun onClick(v: View?) {
            startActivity(Intent(this@CitiesActivity , CarsActivity::class.java))
        }

    }


}

