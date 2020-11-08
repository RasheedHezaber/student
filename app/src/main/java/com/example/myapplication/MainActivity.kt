package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frg)
        if (currentFragment == null) {
            val fragment = Fragmenttwo()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frg, fragment)
                .commit()


        }
    }
}