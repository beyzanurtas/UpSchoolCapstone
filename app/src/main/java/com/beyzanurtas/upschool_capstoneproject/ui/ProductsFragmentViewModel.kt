package com.beyzanurtas.upschool_capstoneproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragmentViewModel : ViewModel() {
    private val productRepo = ProductsRepository()
    var productsList = MutableLiveData<ArrayList<Products>>()

    init {
    }
     fun productsByCategory(category: String){
        productRepo.getProductsByCategory(category)
    }
    fun productById(id: Int){
        productRepo.getProductById(id)
    }
    fun getAllProducts(){
        productRepo.getAllProducts().enqueue(object : Callback<ArrayList<Products>> {
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val allProducts = response.body()
                productsList.value= allProducts
            }
            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            }

        })
    }
    fun getSaleProducts(){
        productRepo.getSaleProducts().enqueue(object: Callback<ArrayList<Products>>{
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val saleProducts = response.body()
                productsList.value= saleProducts
            }
            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            }
        })
    }
    fun getProductsByCategory(category: String){
        productRepo.getProductsByCategory(category).enqueue(object : Callback<ArrayList<Products>>{
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val productsByCategory = response.body()
                productsList.value= productsByCategory
            }
            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            }
        })

    }
}