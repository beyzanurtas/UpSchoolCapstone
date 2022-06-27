package com.beyzanurtas.upschool_capstoneproject.repo

import androidx.lifecycle.MutableLiveData
import com.beyzanurtas.upschool_capstoneproject.entity.Products
import com.beyzanurtas.upschool_capstoneproject.retrofit.ApiUtils
import com.beyzanurtas.upschool_capstoneproject.retrofit.ProductsDaoInterface

class ProductsRepository() {
    private val productsList: MutableLiveData<ArrayList<Products>>
    private val categoriesList: MutableLiveData<ArrayList<String>>
    private val productsDAOInterface: ProductsDaoInterface

    init {
        productsList = MutableLiveData()
        categoriesList = MutableLiveData()
        productsDAOInterface = ApiUtils.getProductsDaoInterface()
    }

    fun getAllProducts() = productsDAOInterface.getAllProducts()

    fun getAllCategories() = productsDAOInterface.getAllCategories()

    fun getSaleProducts() = productsDAOInterface.getSaleProducts()

    fun getProductsByCategory(category: String) =
        productsDAOInterface.getProductsByCategory(category)

    fun getProductById(id: Int) = productsDAOInterface.getProductById(id)

    fun getProductsBySortAndLimit(sort: String, limit: Int) =
        productsDAOInterface.getProductsBySortAndLimit(sort, limit)

    fun addToBag(
        user: String, title: String, price: Double, description: String,
        category: String, image: String, rate: Double, count: Int, sale_state : Int
    ) = productsDAOInterface.addToBag(user, title, price, description, category, image, rate, count, sale_state)

    fun getBagProductsByUser(user: String) = productsDAOInterface.getBagProductsByUser(user)

    fun deleteFromBag(id: Int) = productsDAOInterface.deleteFromBag(id)
}

