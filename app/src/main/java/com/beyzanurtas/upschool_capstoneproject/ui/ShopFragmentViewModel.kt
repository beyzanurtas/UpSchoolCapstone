package com.beyzanurtas.upschool_capstoneproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopFragmentViewModel : ViewModel() {
    private val productRepo = ProductsRepository()
    var categoriesList = MutableLiveData<ArrayList<String>>()

    fun getAllCategories(){
        productRepo.getAllCategories().enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                val allCategories=response.body()
                categoriesList.value= allCategories
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
            }
        })
    }
}