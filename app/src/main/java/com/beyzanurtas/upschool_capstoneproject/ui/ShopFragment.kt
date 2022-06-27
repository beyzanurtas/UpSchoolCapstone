package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzanurtas.upschool_capstoneproject.adapter.ShopAdapter
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private lateinit var bindingFragmentShopBinding: FragmentShopBinding
    private lateinit var viewModel: ShopFragmentViewModel
    private lateinit var adapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false)
        return bindingFragmentShopBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: ShopFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCategories()
        viewModel.categoriesList.observe(viewLifecycleOwner) { categoriesList ->
            adapter = ShopAdapter(categoriesList)

            val layoutManager = LinearLayoutManager(context)
            bindingFragmentShopBinding.shopRecview.layoutManager = layoutManager
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            bindingFragmentShopBinding.shopRecview.adapter = adapter
            bindingFragmentShopBinding.viewAllItemsButton.setOnClickListener {
            val data = "all"
            val action= ShopFragmentDirections.actionShopToProductsFragment(data)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}