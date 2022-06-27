package com.beyzanurtas.upschool_capstoneproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {
        private val productRepo = ProductsRepository()
        var productsList = MutableLiveData<ArrayList<Products>>()

    fun getProductsBySortAndLimit(sort:String,limit:Int){
        productRepo.getProductsBySortAndLimit(sort,limit).enqueue(object : Callback<ArrayList<Products>>{
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val newProducts = response.body()
                productsList.value = newProducts
            }
            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            }
        })
    }
}

