//package com.am.carly.ui.cities
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.am.carly.R
//import com.am.carly.data.model.City
//import com.am.carly.databinding.ItemCityBinding
//
////TODO: Add Trucks types separators to RecyclerView
//class CitiesAdapter(itemClickListener: RecyclerOnItemClickListener) :
//    RecyclerView.Adapter<CitiesAdapter.CityHolder>() {
//    var mItemClickListener: RecyclerOnItemClickListener = itemClickListener
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
//
//        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val binding = DataBindingUtil.inflate<ItemCityBinding>(inflater, R.layout.item_city, parent, false)
//
//        return CityHolder(binding)
//    }
//
//    override fun getItemCount() = truckList.size
//
//    override fun onBindViewHolder(holder: CityHolder, position: Int) = holder.bind(truckList[position])
//
//
//    inner class CityHolder(private val binding: ItemTruckBinding) : RecyclerView.ViewHolder(binding.root),
//
//        View.OnClickListener {
//        private var mTruck: City? = null
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//
//        fun bind(city: City) {
//            this.mTruck = city
//            binding.city = city
//            binding.truckTitleTextView.text = itemView.resources.getString(city.title)
//            if (city.selected) {
//                binding.truckImageView.setImageResource(city.imagePathActivate)
//                binding.truckTitleTextView.setTextColor(itemView.context.resources!!.getColor(R.color.colorAccent))
//            } else {
//                binding.truckTitleTextView.setTextColor(itemView.context.resources!!.getColor(android.R.color.darker_gray))
//                binding.truckImageView.setImageResource(city.imagePathInActivate)
//            }
//            binding.executePendingBindings()
//        }
//
//
//        override fun onClick(v: View?) {
//            mItemClickListener.onItemClick(mTruck!!)
//            for (city in truckList) {
//                city.selected = false
//            }
//            truckList[adapterPosition].selected = true
//            notifyDataSetChanged()
//        }
//    }
//
//    interface RecyclerOnItemClickListener {
//        fun onItemClick(selectedTruck: City)
//    }
//}