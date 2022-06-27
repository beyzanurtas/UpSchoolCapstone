package com.beyzanurtas.upschool_capstoneproject.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.entity.CRUDResponse
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailFragmentViewModel : ViewModel() {
    private val productRepo = ProductsRepository()
    var productsList = MutableLiveData<ArrayList<Products>>()

    fun getProductById(id: Int) {
        productRepo.getProductById(id).enqueue(object : Callback<ArrayList<Products>> {
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                val product = response.body()
                productsList.value = product
            }

            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            }
        })
    }

    fun addToBag(
        user: String, title: String, price: Double, description: String, category: String,
        image: String, rate: Double, count: Int, sale_state : Int
    ) {
        productRepo.addToBag(user, title, price, description, category, image, rate, count, sale_state)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    val bagProducts= response.body()?.message
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    Log.e("addtobag","failure")
                }
            })
    }
}