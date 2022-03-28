package com.example.historyvn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.historyvn.databinding.ActivityMainBinding
import com.example.historyvn.fragment.fr_news
import com.example.historyvn.fragment.informations
import com.example.historyvn.fragment.tests
import com.example.historyvn.fragment.user
import com.google.android.material.bottomnavigation.BottomNavigationView

class News : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_news)

        supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, fr_news()).commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.menu_nbv)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.menu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, fr_news()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.informations -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, informations()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tests -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, tests()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.user -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, user()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
//        bottomNavigationView.setOnItemSelectedListener {
//                item ->
//            when(item.itemId){
//                R.id.add -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, Fragment_add()).commit()
//                    return@setOnItemSelectedListener true
//                }
//                R.id.delete -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, Fragment_delete()).commit()
//                    return@setOnItemSelectedListener true
//                }
//                R.id.items_listview-> {
//                    supportFragmentManager.beginTransaction().replace(R.id.menu_fragment, Fragment_listview()).commit()
//                    return@setOnItemSelectedListener true
//                }
//            }
//            return@setOnItemSelectedListener false
//        }

    }

}