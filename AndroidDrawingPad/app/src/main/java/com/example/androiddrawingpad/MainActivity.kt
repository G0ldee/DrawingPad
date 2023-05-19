package com.example.androiddrawingpad

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introScreenFragment = IntroScreenFragment()
        val drawingPadFragment = DrawingPadFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, introScreenFragment)
            .commit()
    }
}