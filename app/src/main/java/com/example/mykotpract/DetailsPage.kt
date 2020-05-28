package com.example.mykotpract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mykotpract.databinding.ActivityDetailsPageBinding
import com.example.mykotpract.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class DetailsPage : AppCompatActivity() {

   /* lateinit var nametext:TextView
    lateinit var showimg:ImageView
    lateinit var destext:TextView*/

    private lateinit var binding: ActivityDetailsPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_page)
        setTitle("Details")

       /* nametext=findViewById(R.id.dname);
        showimg=findViewById(R.id.dimg);
        destext=findViewById(R.id.ddes);*/

        val n:String=intent.getStringExtra("itemname")
        val i:String=intent.getStringExtra("itemimg")
        val d:String=intent.getStringExtra("itemdes")

        Picasso.get().load(i).into(binding.dimg);
        binding.dname.text=n
        binding.ddes.text=d

       // Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
