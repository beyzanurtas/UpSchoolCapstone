package com.beyzanurtas.upschool_capstoneproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beyzanurtas.upschool_capstoneproject.databinding.CardDesignHomeNewBinding
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.ui.HomeFragment
import com.beyzanurtas.upschool_capstoneproject.ui.HomeFragmentDirections
import com.squareup.picasso.Picasso

class HomeAdapter (var productsList: ArrayList<Products>)
    : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =CardDesignHomeNewBinding.inflate(layoutInflater,parent, false)
        return HomeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val products = productsList.get(position)
        val fragment = HomeFragment()
        val image = holder.binding.productImageView
        Picasso.get().load(products.image_url).into(image)
        holder.binding.productTitleTextView.text=products.product_title
        holder.binding.productPriceTextView.text="$" + products.product_price

        holder.binding.productCardView.setOnClickListener {
            val action=  HomeFragmentDirections.actionHomeToProductFragment(products.id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
       return productsList.size
    }
}
class HomeViewHolder(val binding: CardDesignHomeNewBinding) :
    RecyclerView.ViewHolder(binding.root)
