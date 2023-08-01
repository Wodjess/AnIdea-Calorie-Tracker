package com.example.anidea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anidea.databinding.ActivityGramsBinding

class Grams : AppCompatActivity() {
    lateinit var BindingClass: ActivityGramsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingClass = ActivityGramsBinding.inflate(layoutInflater)
        setContentView(BindingClass.root)

    }
}