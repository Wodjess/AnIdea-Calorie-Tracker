package com.example.anidea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anidea.MemoryOfFood.adapter
import com.example.anidea.databinding.ActivityMainBinding
import com.example.anidea.databinding.ActivityNewfoodBinding
object MemoryOfFood
{
    val ListOfFoodNow = ArrayList<ModelFood>()
    val adapter = ModelFoodAdapter()
}
class newfood : AppCompatActivity() {
    private lateinit var bindingclass: ActivityNewfoodBinding
    lateinit var launch: ActivityResultLauncher<Intent>

    val i = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {

        launch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ // инициализатор
                result: ActivityResult ->
            if(result.resultCode == 100){
                result.data?.getStringExtra("CarbInformation")?.toDouble()
                Log.d("LeshkaKartoshka", aga.ImageUri.toString())
                val mtemp:ModelFood = ModelFood(result.data?.getStringExtra("NameInformation"),result.data?.getStringExtra("CaloriesInformation"),"100 г",result.data?.getStringExtra("FatInformation")?.toDouble(),result.data?.getStringExtra("ProteinInformation")?.toDouble(),result.data?.getStringExtra("CarbInformation")?.toDouble(), aga.ImageUri)
                adapter.addNote(mtemp)
            }
        }
        super.onCreate(savedInstanceState)
        bindingclass = ActivityNewfoodBinding.inflate(layoutInflater)
        setContentView(bindingclass.root)
        init()
        bindingclass.button2.setOnClickListener(){
            launch.launch(Intent(this, addnewfood::class.java))
        }
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if(newFoodMemory.FoodTemp != null){

                    i.putExtra("Name", newFoodMemory.FoodTemp?.name.toString())
                    i.putExtra("potein", newFoodMemory.FoodTemp?.Protein.toString())
                    i.putExtra("fat", newFoodMemory.FoodTemp?.Fat.toString())
                    i.putExtra("carb", newFoodMemory.FoodTemp?.Calories.toString())
                    i.putExtra("calories", newFoodMemory.FoodTemp?.carb.toString())
                    i.putExtra("WhatTheTime", intent.getStringExtra("WhatTheTime"))
                    i.putExtra("Image", newFoodMemory.FoodTemp?.image.toString())
                    Log.d("LeshkaKartoshka", i.getStringExtra("Image").toString())
                    setResult(100,i)
                    finish()
                }
                handler.postDelayed(this, 10)
            }
        }, 0)

    }
    fun init(){
        bindingclass.recyclerView2.layoutManager = GridLayoutManager(this@newfood,1)
        bindingclass.recyclerView2.adapter = adapter
    }
}