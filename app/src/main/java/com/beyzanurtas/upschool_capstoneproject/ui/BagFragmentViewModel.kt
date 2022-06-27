package com.beyzanurtas.upschool_capstoneproject.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.entity.CRUDResponse
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BagFragmentViewModel: ViewModel() {
    private val productRepo = ProductsRepository()
    var productsList = MutableLiveData<ArrayList<Products>>()

    fun getBagProductsByUser(user: String){
        productRepo.getBagProductsByUser(user).enqueue(object : Callback<ArrayList<Products>>{
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val bagProducts = response.body()
                productsList.value= bagProducts
                Log.e("bagproducts",productsList.toString())
            }
            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
                Log.e("bagproducts","failure")
            }
        })
    }
    fun deleteFromBag(id: Int){
        productRepo.deleteFromBag(id).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
            }
        })
    }
    fun cartTotal(cartList: List<Products>) : Double{
        var total : Double = 0.0
        for(i in cartList){
            total += i.product_price.toDouble()
        }
        return total
    }
}


