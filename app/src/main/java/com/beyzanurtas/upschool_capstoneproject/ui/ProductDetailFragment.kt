package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentProductDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {
    private lateinit var bindingProductDetailFragment: FragmentProductDetailBinding
    private lateinit var viewModel: ProductDetailFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingProductDetailFragment=FragmentProductDetailBinding.inflate(inflater,container,false)
        return bindingProductDetailFragment.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : ProductDetailFragmentArgs by navArgs()
        val productId =args.id
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        viewModel.getProductById(productId)

        viewModel.productsList.observe(viewLifecycleOwner) {
            Log.e("product",it.toString())
            bindingProductDetailFragment.apply {
                val product=it[0]
                productTitle.text= product.product_title
                productPrice.text="$" + product.product_price
                description.text=product.product_description
                Picasso.get().load(product.image_url).into(productImage)
                productRatingSingleProduct.rating=product.product_rate.toFloat()

                addToBagButton.setOnClickListener {
                    val title= product.product_title
                    val price= product.product_price.toDouble()
                    val description= product.product_description
                    val category= product.category
                    val image= product.image_url
                    val rate= product.product_rate
                    val count= product.product_count
                    val sale_state= product.sale_state
                    viewModel.addToBag(userId,title,price,description,category,image,rate,count, sale_state)

                    Toast.makeText(activity,"Product added to bag!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
