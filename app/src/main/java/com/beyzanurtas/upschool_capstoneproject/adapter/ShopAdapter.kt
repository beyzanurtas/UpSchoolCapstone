package com.beyzanurtas.upschool_capstoneproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.CardDesignShopBinding
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentShopBinding
import com.beyzanurtas.upschool_capstoneproject.ui.ShopFragmentDirections

class ShopAdapter(var categoriesList: ArrayList<String>)
    : RecyclerView.Adapter<ShopViewHolder>() {
    private lateinit var shopBinding: FragmentShopBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding= CardDesignShopBinding.inflate(layoutInflater,parent,false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
       val categories = categoriesList.get(position)
        holder.binding.categories.text=categories
        holder.binding.productCardView.setOnClickListener {
            val data = categories
            val action=ShopFragmentDirections.actionShopToProductsFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

}
class ShopViewHolder(val binding: CardDesignShopBinding)
    : RecyclerView.ViewHolder(binding.root)