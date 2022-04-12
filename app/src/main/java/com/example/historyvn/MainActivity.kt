package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launchWhenCreated {
            delay(2000)
            val aloading = Intent(this@MainActivity, Activity_loading::class.java)
            finishActivity(0)
            startActivity(aloading)

        }

        /*Handler().postDelayed({
            val Aloading = Intent(this, Activity_loading::class.java)
            startActivity(Aloading)
        }, 2000)*/
    }
}