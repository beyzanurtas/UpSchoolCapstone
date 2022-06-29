package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.adapter.BagAdapter
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentBagBinding
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.retrofit.BagInterface
import com.google.firebase.auth.FirebaseAuth
import java.lang.Math.round

class BagFragment : Fragment() {
    private lateinit var bagBinding: FragmentBagBinding
    private lateinit var viewModel: BagFragmentViewModel
    private lateinit var adapter: BagAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bagBinding=FragmentBagBinding.inflate(inflater,container,false)
        return bagBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: BagFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user= FirebaseAuth.getInstance().currentUser!!.uid

        viewModel.getBagProductsByUser(user)
        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->

            val total= viewModel.cartTotal(productsList)
            if(productsList.isEmpty()){
                bagBinding.emptyBag.visibility= View.VISIBLE
                bagBinding.emptyImage.visibility= View.VISIBLE
                bagBinding.checkoutButton.visibility= View.GONE
                bagBinding.total.visibility= View.GONE
                bagBinding.priceTotal.visibility= View.GONE
            } else {
                bagBinding.emptyBag.visibility= View.GONE
                bagBinding.emptyImage.visibility= View.GONE
                bagBinding.checkoutButton.visibility= View.VISIBLE
                bagBinding.total.visibility= View.VISIBLE
                bagBinding.priceTotal.visibility= View.VISIBLE
            }
            bagBinding.setPriceTotal(total)
            adapter= BagAdapter(productsList, object : BagInterface{
                override fun deleteFromBag(id: Int) {
                    viewModel.deleteFromBag(id)
                    viewModel.getBagProductsByUser(user)
                    viewModel.productsList.observe(viewLifecycleOwner){
                        adapter.updateList(it)

                        Toast.makeText(activity,"Product has been removed from the bag!",Toast.LENGTH_SHORT).show()
                    }
                }
            })
            val layoutManager = LinearLayoutManager(context)
            bagBinding.bagRecview.layoutManager = layoutManager
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            bagBinding.bagRecview.adapter=adapter
        }
        bagBinding.checkoutButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_bag_to_successFragment)
            viewModel.getBagProductsByUser(user)
            viewModel.productsList.observe(viewLifecycleOwner) {
                for (product in it){
                    viewModel.deleteFromBag(product.id)
                }
            }
        }
    }
}

