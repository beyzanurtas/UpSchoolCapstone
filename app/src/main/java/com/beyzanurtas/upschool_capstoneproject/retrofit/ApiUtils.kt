package com.beyzanurtas.upschool_capstoneproject.retrofit


class ApiUtils {
    companion object{
        val base_Url = "https://canerture.com/api/ecommerce/"

        fun getProductsDaoInterface(): ProductsDaoInterface {
            return RetrofitClient.getClient(base_Url).create(ProductsDaoInterface::class.java)
        }
    }
}