package com.am.carly.ui.cities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.am.carly.R
import com.am.carly.data.model.City
import com.am.carly.databinding.ActivityCitiesBinding
import com.am.carly.databinding.ItemCityBinding
import com.am.carly.ui.base.BaseActivity
import com.am.carly.ui.cars.CarsActivity
import com.am.carly.ui.login.StartActivity
import com.am.carly.ui.profile.ProfileActivity
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.heinrichreimersoftware.androidissuereporter.IssueReporterLauncher
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import kotlinx.android.synthetic.main.activity_cities.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class CitiesActivity : BaseActivity(), KodeinAware, NavigationView.OnNavigationItemSelectedListener {
    override val kodein by kodein()
    private val mFactory: CitiesViewModelFactory by instance()
    private lateinit var mBinding: ActivityCitiesBinding
    private lateinit var mViewModel: CitiesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cities)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CitiesViewModel::class.java)
        mBinding.viewModel = mViewModel
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        loadCitiesFromFireStore()


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.getHeaderView(0).findViewById<View>(R.id.clickView).setOnClickListener {
            startActivity(Intent(this@CitiesActivity, ProfileActivity::class.java))
        }
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            com.am.carly.R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


    }

    private fun loadCitiesFromFireStore() {
        val query = FirebaseFirestore.getInstance().collection("cities")
        val options = FirestoreRecyclerOptions.Builder<City>()
            .setQuery(query, City::class.java)
            .setLifecycleOwner(this@CitiesActivity)
            .build()

        val adapter = object : FirestoreRecyclerAdapter<City, CityHolder>(options) {

            override fun onBindViewHolder(holder: CityHolder, position: Int, model: City) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {

                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val binding =
                    DataBindingUtil.inflate<ItemCityBinding>(inflater, com.am.carly.R.layout.item_city, parent, false)

                return CityHolder(binding)
            }
        }
        citiesRecyclerView.adapter = adapter
        citiesRecyclerView.layoutManager = LinearLayoutManager(this@CitiesActivity)
    }

    inner class CityHolder(var binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(city: City) {
            Glide.with(binding.root.context).load(city.imgPath).into(binding.cityImageView)
            binding.cityNameTextView.text = city.name
        }

        override fun onClick(v: View?) {
            startActivity(Intent(this@CitiesActivity, CarsActivity::class.java))
        }
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navAbout -> {
                LibsBuilder()
                    .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                    .withAboutIconShown(true)
                    .withAboutVersionShown(true)
                    .withAboutDescription(getString(com.am.carly.R.string.about_libraries_description))
                    .withFields(com.am.carly.R.string::class.java.fields)
                    .start(this@CitiesActivity)
            }
            R.id.navBugReport -> {
                IssueReporterLauncher.forTarget("Abed-Murad", "Carly")
                    .guestToken("54a7b82d0f9757f7fb85347d7b64950161507b48")
                    .guestEmailRequired(false)
                    .minDescriptionLength(20)
                    .homeAsUpEnabled(true)
                    .launch(this@CitiesActivity)
            }
            R.id.logout -> {

                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        // user is now signed out
                        startActivity(Intent(this@CitiesActivity, StartActivity::class.java))
                        finish()
                    }

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}

