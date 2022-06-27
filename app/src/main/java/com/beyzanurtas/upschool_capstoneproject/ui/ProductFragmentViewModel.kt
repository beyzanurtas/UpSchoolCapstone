package com.beyzanurtas.upschool_capstoneproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beyzanurtas.upschool_capstoneproject.repo.ProductsRepository
import com.beyzanurtas.upschool_capstoneproject.entity.Products

class ProductFragmentViewModel:ViewModel() {
    private val pdaor = ProductsRepository()
    var productsList = MutableLiveData<List<Products>>()

    init{
    }
}