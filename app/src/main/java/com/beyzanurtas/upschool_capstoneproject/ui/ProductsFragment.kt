package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzanurtas.upschool_capstoneproject.adapter.ProductsAdapter
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private lateinit var productsFragmentBinding: FragmentProductsBinding
    private lateinit var viewModel: ProductsFragmentViewModel
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsFragmentBinding=FragmentProductsBinding.inflate(inflater,container,false)
        return productsFragmentBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:ProductsFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ProductsFragmentArgs by navArgs()
        val data = args.data

        if(data=="all"){
            viewModel.getAllProducts()
            viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
               adapter= ProductsAdapter(productsList)

                val layoutManager = LinearLayoutManager(context)
                productsFragmentBinding.productsRecyclerview.layoutManager = layoutManager
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                productsFragmentBinding.productsRecyclerview.adapter = adapter
            }
        } else if (data=="sale"){
            viewModel.getSaleProducts()
            viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
                adapter= ProductsAdapter(productsList)

                val layoutManager = LinearLayoutManager(context)
                productsFragmentBinding.productsRecyclerview.layoutManager = layoutManager
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                productsFragmentBinding.productsRecyclerview.adapter = adapter
            }
        } else {
            viewModel.getProductsByCategory(category = data!!)
            viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
                adapter = ProductsAdapter(productsList)

                val layoutManager = LinearLayoutManager(context)
                productsFragmentBinding.productsRecyclerview.layoutManager = layoutManager
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                productsFragmentBinding.productsRecyclerview.adapter = adapter
            }
        }
    }
}