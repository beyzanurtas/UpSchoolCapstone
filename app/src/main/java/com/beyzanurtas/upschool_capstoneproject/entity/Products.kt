package com.beyzanurtas.upschool_capstoneproject.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Products(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("user")
    @Expose
    var user: String,
    @SerializedName("title")
    @Expose
    var product_title: String,
    @SerializedName("price")
    @Expose
    var product_price: String,
    @SerializedName("description")
    @Expose
    var product_description: String,
    @SerializedName("category")
    @Expose
    var category: String,
    @SerializedName("image")
    @Expose
    var image_url: String,
    @SerializedName("rate")
    @Expose
    var product_rate: Double,
    @SerializedName("count")
    @Expose
    var product_count: Int,
    @SerializedName("sale_state")
    @Expose
    var sale_state: Int
) : Serializable  {
}