package com.example.anidea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.anidea.databinding.ActivityMainBinding
import com.example.anidea.databinding.ActivityNewfoodBinding
import com.example.anidea.size.adapter
import com.example.anidea.size.adaptery
import com.example.anidea.size.adapterz
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

object DetailInformation
{
    public var allcalories:Double = 0.toDouble()
    public var allproteins:Double = 0.toDouble()
    public var allcarbs:Double = 0.toDouble()
    public var allfats:Double = 0.toDouble()
    public var ischanged = false
}
object GoalInformation
{
    public var allcalories:Double = 0.toDouble()
    public var allproteins:Double = 0.toDouble()
    public var allcarbs:Double = 0.toDouble()
    public var allfats:Double = 0.toDouble()
}
class MainActivity : AppCompatActivity(){
    lateinit var launch: ActivityResultLauncher<Intent>
    lateinit var launch2: ActivityResultLauncher<Intent>
    lateinit var BindingClass: ActivityMainBinding
    lateinit var result: ActivityResult
    var tempstr = "100"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(BindingClass.root)
        launch2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result1: ActivityResult ->
            if(result1.resultCode == 100){
                tempstr = result1.data?.getStringExtra("export").toString()
                DetailInformation.allcalories += ((tempstr.toDouble() / 100).toDouble() * result.data?.getStringExtra("potein").toString().toDouble())
                DetailInformation.allproteins += ((tempstr.toDouble() / 100).toDouble() * result.data?.getStringExtra("calories").toString().toDouble())
                DetailInformation.allfats += ((tempstr.toDouble() / 100).toDouble() * result.data?.getStringExtra("fat").toString().toDouble())
                DetailInformation.allcarbs += ((tempstr.toDouble() / 100).toDouble() * result.data?.getStringExtra("carb").toString().toDouble())
                DetailInformation.ischanged = true

                when(result.data?.getStringExtra("WhatTheTime").toString()){
                    "Breakfast" -> {val mtemp:Model = Model(result.data?.getStringExtra("Name").toString(),tempstr,result.data?.getStringExtra("potein").toString(),result.data?.getStringExtra("calories").toString(),result.data?.getStringExtra("fat").toString(),result.data?.getStringExtra("carb").toString(),2, result.data?.getStringExtra("Image")?.toUri())
                        adapterz.addNote(mtemp)}
                    "Dinner" -> {val mtemp:Model = Model(result.data?.getStringExtra("Name").toString(),tempstr,result.data?.getStringExtra("potein").toString(),result.data?.getStringExtra("calories").toString(),result.data?.getStringExtra("fat").toString(),result.data?.getStringExtra("carb").toString(),0, result.data?.getStringExtra("Image")?.toUri())
                        adapter.addNote(mtemp)}
                    "Lunch" -> {val mtemp:Model = Model(result.data?.getStringExtra("Name").toString(),tempstr,result.data?.getStringExtra("potein").toString(),result.data?.getStringExtra("calories").toString(),result.data?.getStringExtra("fat").toString(),result.data?.getStringExtra("carb").toString(),1, result.data?.getStringExtra("Image")?.toUri())
                        adaptery.addNote(mtemp)}
                }
            }
        }
        launch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode == 100){
                this.result = result
                var i22 = Intent(this, howmuchgrams::class.java)
                i22.putExtra("Name", newFoodMemory.FoodTemp?.name.toString())
                i22.putExtra("potein", newFoodMemory.FoodTemp?.Protein.toString())
                i22.putExtra("fat", newFoodMemory.FoodTemp?.Fat.toString())
                i22.putExtra("carb", newFoodMemory.FoodTemp?.carb.toString())
                i22.putExtra("calories", newFoodMemory.FoodTemp?.Calories.toString())
                i22.putExtra("WhatTheTime", intent.getStringExtra("WhatTheTime"))
                i22.putExtra("Image", newFoodMemory.FoodTemp?.image.toString())
                newFoodMemory.FoodTemp = null
                launch2.launch(i22)
            }
        }
        BindingClass.button1.setOnClickListener {
            var i = Intent(this, newfood::class.java)
            i.putExtra("WhatTheTime","Breakfast")
            launch.launch(i)
        }
        BindingClass.button.setOnClickListener(){
            var i = Intent(this, newfood::class.java)
            i.putExtra("WhatTheTime","Lunch")
            launch.launch(i)
        }
        BindingClass.button22.setOnClickListener(){
            var i = Intent(this, newfood::class.java)
            i.putExtra("WhatTheTime","Dinner")
            launch.launch(i)
        }
        BindingClass.imageView3.setOnClickListener(){
            BindingClass.imageView3.animate().apply {
                duration = 500
                rotationBy(540f)
            }.start()
            if(BindingClass.allitemslayout.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
                BindingClass.allitemslayout.visibility = View.VISIBLE
            }
            else{
                TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
                BindingClass.allitemslayout.visibility = View.GONE
            }
        }
        BindingClass.imageView4.setOnClickListener(){
            BindingClass.imageView4.animate().apply {
                duration = 500
                rotationBy(540f)
            }.start()
            if(BindingClass.allitemslayout0.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(BindingClass.cardView0,AutoTransition())
                BindingClass.allitemslayout0.visibility = View.VISIBLE
            }
            else{
                TransitionManager.beginDelayedTransition(BindingClass.cardView0,AutoTransition())
                BindingClass.allitemslayout0.visibility = View.GONE
            }
        }
        BindingClass.imageView2.setOnClickListener(){
            BindingClass.imageView2.animate().apply {
                duration = 500
                rotationBy(540f)
            }.start()
            if(BindingClass.allitemslayout1.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(BindingClass.cardView1,AutoTransition())
                BindingClass.allitemslayout1.visibility = View.VISIBLE
            }
            else{
                TransitionManager.beginDelayedTransition(BindingClass.cardView1,AutoTransition())
                BindingClass.allitemslayout1.visibility = View.GONE
            }
        }
        init()

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {

                if(DetailInformation.ischanged){
                    BindingClass.textView10.text = DetailInformation.allcalories.roundToInt().toString() + " / " + GoalInformation.allcalories.roundToInt()
                    BindingClass.textView61.text = "Б\n" + DetailInformation.allproteins.roundToInt().toString() + " / " + GoalInformation.allproteins.roundToInt()
                    BindingClass.textView81.text = "Ж\n" + DetailInformation.allfats.roundToInt().toString() + " / " + GoalInformation.allfats.roundToInt()
                    BindingClass.textView9.text = "У\n" + DetailInformation.allcarbs.roundToInt().toString() + " / " + GoalInformation.allcarbs.roundToInt()
                    DetailInformation.ischanged = false
                }

                handler.postDelayed(this, 10)
            }
        }, 0)
        TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
        BindingClass.allitemslayout.visibility = View.GONE
        TransitionManager.beginDelayedTransition(BindingClass.cardView0,AutoTransition())
        BindingClass.allitemslayout0.visibility = View.GONE
        TransitionManager.beginDelayedTransition(BindingClass.cardView1,AutoTransition())
        BindingClass.allitemslayout1.visibility = View.GONE
    }


    fun init(){
        BindingClass.recyclerView.layoutManager = GridLayoutManager(this@MainActivity,1)
        BindingClass.recyclerView.adapter = adapter

        BindingClass.recyclerView0.layoutManager = GridLayoutManager(this@MainActivity,1)
        BindingClass.recyclerView0.adapter = adaptery

        BindingClass.recyclerView1.layoutManager = GridLayoutManager(this@MainActivity,1)
        BindingClass.recyclerView1.adapter = adapterz

    }
}