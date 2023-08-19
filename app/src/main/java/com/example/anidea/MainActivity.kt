package com.example.anidea

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.anidea.databinding.ActivityMainBinding
import com.example.anidea.size.adapter
import com.example.anidea.size.adaptery
import com.example.anidea.size.adapterz
import java.text.SimpleDateFormat
import java.util.Date
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
                if(DetailInformation.ischanged){
                    BindingClass.textView10.text = DetailInformation.allcalories.roundToInt().toString() + " / " + GoalInformation.allcalories.roundToInt()
                    BindingClass.textView61.text = "Б\n" + DetailInformation.allproteins.roundToInt().toString() + " / " + GoalInformation.allproteins.roundToInt()
                    BindingClass.textView81.text = "Ж\n" + DetailInformation.allfats.roundToInt().toString() + " / " + GoalInformation.allfats.roundToInt()
                    BindingClass.textView9.text = "У\n" + DetailInformation.allcarbs.roundToInt().toString() + " / " + GoalInformation.allcarbs.roundToInt()
                    DetailInformation.ischanged = false
                    makeinsane()
                }
            }
        }
        if(SimpleDateFormat("M").format(Date()).toString().toInt() <= 9)
        {
            BindingClass.textView11.text = SimpleDateFormat("dd\n0M").format(Date()).toString()
        }
        else
        {
            BindingClass.textView11.text = SimpleDateFormat("dd\nM").format(Date()).toString()
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
        BindingClass.cardView8.setOnClickListener(){
            TODO("IMPLEMENT DATE VIEWER")
        }
        BindingClass.theprofilephoto.setOnClickListener()
        {
            var i = Intent(this, profile::class.java)
            startActivity(i)
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
        var isOpened = false
        var doNothing = false
        var listener = View.OnTouchListener(function = {view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                if(doNothing){
                    doNothing = false
                    if(!isOpened){
                        isOpened = true
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(0f)
                        }
                    }
                    else{
                        isOpened = false
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(450f)
                        }
                    }
                }
                else {
                    if (motionEvent.rawY - view.height / 2 >= 1544 && motionEvent.rawY - view.height / 2 <= 2000) {

                    }
                }
            }
            Log.d("LeshkaDebug", motionEvent.action.toString())
            if(motionEvent.action == 0)
            {
                doNothing = true
            }
            if(motionEvent.action == MotionEvent.ACTION_UP){
                if(doNothing){
                    doNothing = false
                    if(!isOpened){
                        isOpened = true
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(0f)
                        }
                    }
                    else{
                        isOpened = false
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(450f)
                        }
                    }
                }
                else{
                    if(motionEvent.rawY - view.height/2 <= 1700 && isOpened == false){
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(0f)
                        }
                        isOpened = true
                    }
                    if(isOpened == true && motionEvent.rawY - view.height/2 > 1700){
                        BindingClass.CaloriesDetailCardView.animate().apply {
                            duration = 200
                            translationY(450f)
                        }
                        isOpened = false
                    }
                }
            }
            true

        })

        // Перетаскивание CaloriesDetailCardView
        BindingClass.CaloriesDetailCardView.setOnTouchListener(listener)

        BindingClass.imageView3.setOnClickListener(){
                BindingClass.imageView3.animate().apply {
                    duration = 500
                    rotationBy(540f)
                }.start()
                if(BindingClass.allitemslayout.visibility == View.GONE){
                    TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
                    BindingClass.allitemslayout.visibility = View.VISIBLE
                    //InsaneMemory.nowopenedobj += InsaneMemory.thirdobj
                    makeinsane()
                }
                else{
                    TransitionManager.beginDelayedTransition(BindingClass.cardView,AutoTransition())
                    BindingClass.allitemslayout.visibility = View.GONE
                    //InsaneMemory.nowopenedobj -= InsaneMemory.thirdobj
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
                    //InsaneMemory.nowopenedobj += InsaneMemory.lastobj
                    BindingClass.allitemslayout0.visibility = View.VISIBLE
                } else {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView0,
                        AutoTransition()
                    )
                    BindingClass.allitemslayout0.visibility = View.GONE
                    //InsaneMemory.nowopenedobj -= InsaneMemory.thirdobj
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
                    //InsaneMemory.nowopenedobj += InsaneMemory.secondobj
                    makeinsane()
                } else {
                    TransitionManager.beginDelayedTransition(
                        BindingClass.cardView1,
                        AutoTransition()
                    )
                    BindingClass.allitemslayout1.visibility = View.GONE
                    //InsaneMemory.nowopenedobj -= InsaneMemory.secondobj
                    makeinsane(true)
                }
        }
        init()
        BindingClass.scrollView2.getViewTreeObserver().addOnScrollChangedListener(OnScrollChangedListener {
            if(BindingClass.scrollView2.scrollY >= 40){
                var progress = 0f
                BindingClass.toolbar2.visibility = View.VISIBLE
                progress = (40 - BindingClass.scrollView2.scrollY.toString().toInt()).toFloat() * -1
                if(progress <= 37){
                    BindingClass.toolbar2.alpha = progress * 2.5f / 100f
                }
                if(progress > 37){
                    BindingClass.toolbar2.alpha = 0.96f
                }
            }
            else{
                BindingClass.toolbar2.alpha = 0f
                BindingClass.toolbar2.visibility = View.INVISIBLE
            }
        })
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if(DetailInformation.ischanged){
                    BindingClass.textView10.text = DetailInformation.allcalories.roundToInt().toString() + " / " + GoalInformation.allcalories.roundToInt()
                    BindingClass.textView61.text = "Б\n" + DetailInformation.allproteins.roundToInt().toString() + " / " + GoalInformation.allproteins.roundToInt()
                    BindingClass.textView81.text = "Ж\n" + DetailInformation.allfats.roundToInt().toString() + " / " + GoalInformation.allfats.roundToInt()
                    BindingClass.textView9.text = "У\n" + DetailInformation.allcarbs.roundToInt().toString() + " / " + GoalInformation.allcarbs.roundToInt()
                    DetailInformation.ischanged = false
                    makeinsane()
                }
                if(InsaneMemory.nowopenedobj != 0f){
                    InsaneMemory.nowopenedobj = 0f
                }
                else
                {
                    if(BindingClass.allitemslayout1.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.secondobj
                    }
                    if(BindingClass.allitemslayout0.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.lastobj
                    }
                    if(BindingClass.allitemslayout.visibility == View.VISIBLE)
                    {
                        InsaneMemory.nowopenedobj += InsaneMemory.thirdobj - InsaneMemory.secondobj
                    }
                    if(InsaneMemory.nowopenedobj >= 800f)
                    {
                        allsize(((InsaneMemory.nowopenedobj - 700f) / 100).roundToInt())
                    }
                    if(InsaneMemory.nowopenedobj < 999f)
                    {
                        allsize(0)
                    }
                }
                handler.postDelayed(this, 300)
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
    var strvdasgfv = ""
    fun allsize(size:Int){
        strvdasgfv = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        if (size != 0){
            for(i in 0..size){
                strvdasgfv += "\n"
            }
        }
        BindingClass.theheightofthepage.text = strvdasgfv
    }
    fun makeinsane(IsClosing:Boolean = false){
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
