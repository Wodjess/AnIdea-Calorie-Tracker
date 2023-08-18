package com.example.anidea

import android.R.attr.height
import android.R.attr.width
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.anidea.databinding.ActivityMainBinding
import com.example.anidea.size.adapter
import com.example.anidea.size.adaptery
import com.example.anidea.size.adapterz
import kotlin.math.roundToInt


object DetailInformation
{
    public var allcalories:Double = 0.toDouble()
    public var allproteins:Double = 0.toDouble()
    public var allcarbs:Double = 0.toDouble()
    public var allfats:Double = 0.toDouble()
    public var ischanged = false
}
object InsaneMemory
{
    var secondobj:Float = 0f
    var thirdobj:Float = 0f
    var tempobj:Float = 0f
    var lastobj:Float = 0f
    var nowopenedobj:Float = 0f
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
                        adapterz.addNote(mtemp)
                        InsaneMemory.secondobj += 100f; InsaneMemory.thirdobj += 100f; InsaneMemory.tempobj += 100f}
                    "Dinner" -> {val mtemp:Model = Model(result.data?.getStringExtra("Name").toString(),tempstr,result.data?.getStringExtra("potein").toString(),result.data?.getStringExtra("calories").toString(),result.data?.getStringExtra("fat").toString(),result.data?.getStringExtra("carb").toString(),0, result.data?.getStringExtra("Image")?.toUri())
                        adapter.addNote(mtemp)
                        InsaneMemory.thirdobj += 100f}
                    "Lunch" -> {val mtemp:Model = Model(result.data?.getStringExtra("Name").toString(),tempstr,result.data?.getStringExtra("potein").toString(),result.data?.getStringExtra("calories").toString(),result.data?.getStringExtra("fat").toString(),result.data?.getStringExtra("carb").toString(),1, result.data?.getStringExtra("Image")?.toUri())
                        adaptery.addNote(mtemp); InsaneMemory.lastobj += 100f}
                }
                makeinsane()
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
                    InsaneMemory.nowopenedobj += InsaneMemory.thirdobj
                    makeinsane()
                }
                else{
                    TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
                    BindingClass.allitemslayout.visibility = View.GONE
                    InsaneMemory.nowopenedobj -= InsaneMemory.thirdobj
                    makeinsane(true)
                }
        }
        BindingClass.imageView4.setOnClickListener(){
                BindingClass.imageView4.animate().apply {
                    duration = 500
                    rotationBy(540f)
                }.start()
                if (BindingClass.allitemslayout0.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView0,
                        AutoTransition()
                    )
                    InsaneMemory.nowopenedobj += InsaneMemory.lastobj
                    BindingClass.allitemslayout0.visibility = View.VISIBLE
                } else {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView0,
                        AutoTransition()
                    )
                    BindingClass.allitemslayout0.visibility = View.GONE
                    InsaneMemory.nowopenedobj -= InsaneMemory.thirdobj
                }
                makeinsane()
        }

        BindingClass.imageView2.setOnClickListener(){
                BindingClass.imageView2.animate().apply {
                    duration = 500
                    rotationBy(540f)
                }.start()
                if (BindingClass.allitemslayout1.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView1,
                        AutoTransition()
                    )
                    BindingClass.allitemslayout1.visibility = View.VISIBLE
                    InsaneMemory.nowopenedobj += InsaneMemory.secondobj
                    makeinsane()
                } else {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView1,
                        AutoTransition()
                    )
                    BindingClass.allitemslayout1.visibility = View.GONE
                    InsaneMemory.nowopenedobj -= InsaneMemory.secondobj
                    makeinsane(true)
                }
        }
        init()

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                Log.d("LeshkaDebug",InsaneMemory.nowopenedobj.toString())
                if(BindingClass.CaloriesDetailCardView.alpha == 0f){
                    BindingClass.CaloriesDetailCardView.animate().apply {
                        duration = 150
                        alpha(1f)
                    }
                }
                if(DetailInformation.ischanged){
                    BindingClass.textView10.text = DetailInformation.allcalories.roundToInt().toString() + " / " + GoalInformation.allcalories.roundToInt()
                    BindingClass.textView61.text = "Б\n" + DetailInformation.allproteins.roundToInt().toString() + " / " + GoalInformation.allproteins.roundToInt()
                    BindingClass.textView81.text = "Ж\n" + DetailInformation.allfats.roundToInt().toString() + " / " + GoalInformation.allfats.roundToInt()
                    BindingClass.textView9.text = "У\n" + DetailInformation.allcarbs.roundToInt().toString() + " / " + GoalInformation.allcarbs.roundToInt()
                    DetailInformation.ischanged = false


                    InsaneMemory.nowopenedobj = 0f
                    if(BindingClass.allitemslayout1.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.secondobj
                    }
                    if(BindingClass.allitemslayout0.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.thirdobj
                    }
                    if(BindingClass.allitemslayout.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.lastobj
                    }
                    makeinsane()
                }
                handler.postDelayed(this, 100)
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
    var strvdasgfv = "\n"
    fun allsize(size:Int){
        strvdasgfv = ""
        if (size != 0){
            for(i in 0..size){
                strvdasgfv += "\n"
            }
        }
        BindingClass.theheightofthepage.text = strvdasgfv
        BindingClass.CaloriesDetailCardView.alpha = 0f
    }
    fun makeinsane(IsClosing:Boolean = false){
        if(InsaneMemory.nowopenedobj >= 1000f)
        {

            allsize(((InsaneMemory.nowopenedobj - 900f) / 100 + 11).roundToInt())
        }
        if(InsaneMemory.nowopenedobj < 999f)
        {
            allsize(0)
        }
        if (!IsClosing){
            if(BindingClass.allitemslayout.visibility == View.VISIBLE && BindingClass.allitemslayout1.visibility == View.VISIBLE){
                BindingClass.cardView.animate().apply { //Если все открыты
                    duration = 500
                    translationY(InsaneMemory.secondobj)
                }
                BindingClass.cardView0.animate().apply {
                    duration = 500
                    translationY(InsaneMemory.thirdobj)
                }
            }
            if(BindingClass.allitemslayout1.visibility == View.VISIBLE && BindingClass.allitemslayout.visibility != View.VISIBLE){
                BindingClass.cardView.animate().apply { //Если открыта только 1
                    duration = 500
                    translationY(InsaneMemory.tempobj)
                }
                BindingClass.cardView0.animate().apply {
                    duration = 500
                    translationY(InsaneMemory.tempobj)
                }
            }
            if(BindingClass.allitemslayout.visibility == View.VISIBLE && BindingClass.allitemslayout1.visibility != View.VISIBLE){
                BindingClass.cardView0.animate().apply { //Если открыта только 2
                    duration = 500
                    translationY(InsaneMemory.thirdobj - InsaneMemory.tempobj)
                }
            }
        }
        else{
            if(BindingClass.allitemslayout.visibility == View.GONE && BindingClass.allitemslayout1.visibility == View.GONE){ //Все закрыты
                BindingClass.cardView.animate().apply {
                    duration = 500
                    translationY(0f)
                }
                BindingClass.cardView0.animate().apply {
                    duration = 500
                    translationY(0f)
                }
            }
            if(BindingClass.allitemslayout.visibility == View.VISIBLE && BindingClass.allitemslayout1.visibility == View.GONE){
                BindingClass.cardView.animate().apply {
                    duration = 500
                    translationY(0f)
                }
                BindingClass.cardView0.animate().apply { //Если открыта только 2
                    duration = 500
                    translationY(InsaneMemory.thirdobj - InsaneMemory.tempobj)
                }
            }
            if(BindingClass.allitemslayout.visibility == View.GONE && BindingClass.allitemslayout1.visibility == View.VISIBLE){
                BindingClass.cardView.animate().apply { //Если открыта только 1
                    duration = 500
                    translationY(InsaneMemory.tempobj)
                }
                BindingClass.cardView0.animate().apply {
                    duration = 500
                    translationY(InsaneMemory.tempobj)
                }
            }
        }
    }
}