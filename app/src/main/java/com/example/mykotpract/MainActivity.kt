package com.example.mykotpract

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.mykotpract.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonClick.setOnClickListener {
            startActivity(Intent(this,Second::class.java))

        }
    }

    /*fun donext(view: View) {
        startActivity(Intent(this,Second::class.java))

    }*/
}
