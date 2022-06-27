package com.beyzanurtas.upschool_capstoneproject.retrofit

import com.beyzanurtas.upschool_capstoneproject.entity.CRUDResponse
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDaoInterface {

    @GET("get_categories.php")
    fun getAllCategories(): Call<ArrayList<String>>

    @GET("get_products.php")
    fun getAllProducts(): Call<ArrayList<Products>>

    @GET("get_sale_products.php")
    fun getSaleProducts(): Call<ArrayList<Products>>

    @POST("get_products_by_category.php")
    @FormUrlEncoded
    fun getProductsByCategory(@Field("category") category: String): Call<ArrayList<Products>>

    @POST("get_product_by_id.php")
    @FormUrlEncoded
    fun getProductById(@Field("id") id: Int): Call<ArrayList<Products>>

    @POST("get_products.php")
    @FormUrlEncoded
    fun getProductsBySortAndLimit(
        @Field("sort") sort: String,
        @Field("limit") limit: Int
    ): Call<ArrayList<Products>>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: Double,
        @Field("count") count: Int,
        @Field("sale_state") sale_state: Int
    ): Call<CRUDResponse>

    @POST("delete_from_bag.php")
    @FormUrlEncoded
    fun deleteFromBag(@Field("id") id: Int): Call<CRUDResponse>

    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProductsByUser(@Field("user") user: String): Call<ArrayList<Products>>
}