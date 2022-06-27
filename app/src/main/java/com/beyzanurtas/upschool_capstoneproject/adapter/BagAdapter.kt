package com.beyzanurtas.upschool_capstoneproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beyzanurtas.upschool_capstoneproject.databinding.CardDesignBagBinding
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.retrofit.BagInterface
import com.beyzanurtas.upschool_capstoneproject.ui.BagFragment
import com.squareup.picasso.Picasso

class BagAdapter(var productsList: ArrayList<Products>, private val bagInterface: BagInterface)
    : RecyclerView.Adapter<BagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardDesignBagBinding.inflate(layoutInflater, parent, false)
        return BagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        val products = productsList.get(position)
        val fragment = BagFragment()
        val image = holder.binding.productImageView
        Picasso.get().load(products.image_url).into(image)
        holder.binding.productTitleTextView.text = products.product_title
        holder.binding.productPriceTextView.text ="$" + products.product_price

        holder.binding.deleteFromBagButton.setOnClickListener {
            bagInterface.deleteFromBag(products.id)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
    fun updateList(list: ArrayList<Products>){
        productsList=list
        notifyDataSetChanged()
    }
}

class BagViewHolder(val binding: CardDesignBagBinding)
    : RecyclerView.ViewHolder(binding.root)
