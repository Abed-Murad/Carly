package com.am.carly.ui.cities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.am.carly.ui.cars.AddCarActivity
import com.am.carly.ui.cars.CarsActivity
import com.am.carly.ui.profile.ProfileActivity
import com.am.carly.util.PARM_CITY_CODE
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.heinrichreimersoftware.androidissuereporter.IssueReporterLauncher
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_cities.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class CitiesActivity : BaseActivity(), KodeinAware, NavigationView.OnNavigationItemSelectedListener {
    override val kodein by kodein()
    private val mFactory: CitiesViewModelFactory by instance()
    private lateinit var mBinding: ActivityCitiesBinding
    private lateinit var mViewModel: CitiesViewModel
    private lateinit var mNavView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cities)
        mViewModel = ViewModelProviders.of(this, mFactory).get(CitiesViewModel::class.java)
        mBinding.viewModel = mViewModel
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        loadCitiesFromFireStore()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        mNavView = findViewById(R.id.nav_view)
        populateNabHeaderViews()


        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            com.am.carly.R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavView.setNavigationItemSelectedListener(this)


    }

    private fun populateNabHeaderViews() {
        val navHeaderView = mNavView.getHeaderView(0)

        navHeaderView.findViewById<View>(R.id.clickView).setOnClickListener {
            startActivity(Intent(this@CitiesActivity, ProfileActivity::class.java))
        }

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            navHeaderView.findViewById<TextView>(R.id.userNameTextView).text = name
            navHeaderView.findViewById<TextView>(R.id.userEmailTextView).text = email
            Glide.with(this@CitiesActivity).load(photoUrl)
                .into(navHeaderView.findViewById<CircularImageView>(R.id.userImageView))
        }
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
                    DataBindingUtil.inflate<ItemCityBinding>(inflater, R.layout.item_city, parent, false)

                return CityHolder(binding)
            }
        }
        citiesRecyclerView.adapter = adapter
        citiesRecyclerView.layoutManager = LinearLayoutManager(this@CitiesActivity)
    }

    inner class CityHolder(var binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var mCity: City

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(city: City) {
            mCity = city
            Glide.with(binding.root.context).load(city.imgPath).into(binding.cityImageView)
            binding.cityNameTextView.text = city.name
        }

        override fun onClick(v: View?) {
            startActivity(Intent(this@CitiesActivity, CarsActivity::class.java).also {
                it.putExtra(PARM_CITY_CODE, mCity.name)
            })
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
            R.id.navAddCar -> {
                startActivity(Intent(this@CitiesActivity, AddCarActivity::class.java))
            }
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

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}

