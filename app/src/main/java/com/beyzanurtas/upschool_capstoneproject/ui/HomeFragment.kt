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
import com.beyzanurtas.upschool_capstoneproject.HomeAdapter
import com.beyzanurtas.upschool_capstoneproject.adapter.ProductsAdapter
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var bindingFragmentHomeBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var adapter : HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentHomeBinding =  FragmentHomeBinding.inflate(inflater, container, false)

               return bindingFragmentHomeBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: HomeFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductsBySortAndLimit(sort = "desc", limit = 10)
        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            adapter = HomeAdapter(productsList)

            val layoutManager = LinearLayoutManager(context)
            bindingFragmentHomeBinding.homeRecyclerview.layoutManager = layoutManager
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL

            bindingFragmentHomeBinding.homeRecyclerview.adapter = adapter
            }

            bindingFragmentHomeBinding.saleProductsButton.setOnClickListener {
                val data = "sale"
                val action= HomeFragmentDirections.actionHomeToProductsFragment(data)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }


