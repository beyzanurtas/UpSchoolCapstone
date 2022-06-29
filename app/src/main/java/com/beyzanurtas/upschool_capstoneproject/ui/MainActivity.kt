package com.beyzanurtas.upschool_capstoneproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.beyzanurtas.upschool_capstoneproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment

        val navController=navHostFragment.navController

        val bottomNavigationView: BottomNavigationView =findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
               R.id.productFragment -> {
                   bottomNavigationView.visibility=View.GONE
                   findViewById<TextView>(R.id.toolbar_label).text="Product Detail"
               }
                R.id.home -> {
                    bottomNavigationView.visibility=View.VISIBLE
                    findViewById<TextView>(R.id.toolbar_label).text=""
                }
                R.id.productsFragment -> {
                    bottomNavigationView.visibility=View.VISIBLE
                    findViewById<TextView>(R.id.toolbar_label).text="Products"
                }
                R.id.shop -> {
                    bottomNavigationView.visibility=View.VISIBLE
                    findViewById<TextView>(R.id.toolbar_label).text="Categories"
                }
                R.id.bag -> {
                    bottomNavigationView.visibility=View.VISIBLE
                    findViewById<TextView>(R.id.toolbar_label).text="Bag"
                }
                else ->  findViewById<TextView>(R.id.toolbar_label).text=""
            }
        }
        val toolbar= findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val appBarConfiguration= AppBarConfiguration(setOf(R.id.home))
        toolbar.setupWithNavController(navController,appBarConfiguration)
    }
    override fun onSupportNavigateUp(): Boolean {
        applicationContext
        findNavController(R.id.fragmentContainerView).popBackStack()
        return true
    }

}