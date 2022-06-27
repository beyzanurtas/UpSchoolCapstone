package com.beyzanurtas.upschool_capstoneproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.CardDesignProductsBinding
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.retrofit.BagInterface
import com.beyzanurtas.upschool_capstoneproject.ui.ProductsFragment
import com.beyzanurtas.upschool_capstoneproject.ui.ProductsFragmentDirections
import com.beyzanurtas.upschool_capstoneproject.ui.ShopFragmentDirections
import com.squareup.picasso.Picasso

class ProductsAdapter(var productsList : ArrayList<Products>)
    : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding= CardDesignProductsBinding.inflate(layoutInflater,parent,false)
        return ProductsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = productsList.get(position)
        val fragment: ProductsFragment
        val image = holder.binding.productImageView
        Picasso.get().load(products.image_url).into(image)
        holder.binding.productTitleTextView.text=products.product_title
        holder.binding.productPriceTextView.text="$" + products.product_price
        holder.binding.productRatingSingleProduct.rating=products.product_rate.toFloat()
        holder.binding.productCardView.setOnClickListener {

           val action=  ProductsFragmentDirections.actionProductsFragmentToProductFragment(products.id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return productsList.size
    }
}
class ProductsViewHolder(val binding: CardDesignProductsBinding)
    : RecyclerView.ViewHolder(binding.root)