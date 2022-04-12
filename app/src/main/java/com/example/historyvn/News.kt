package com.example.historyvn

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.historyvn.databinding.ActivityMainBinding
import com.example.historyvn.fragment.FragmentNews
import com.example.historyvn.fragment.FragmentInformations
import com.example.historyvn.fragment.FragmentTests
import com.example.historyvn.fragment.user
import com.google.android.material.bottomnavigation.BottomNavigationView

class News : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.menu_nbv)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)
    }
}