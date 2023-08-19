package com.example.anidea

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color.alpha
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.anidea.databinding.ActivityProfileBinding
import kotlin.math.roundToInt


class profile : AppCompatActivity() {
    lateinit var BindingClass: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingClass = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(BindingClass.root)
        BindingClass.textView23.text = DetailInformation.allcalories.roundToInt().toString() + " / " + GoalInformation.allcalories.roundToInt().toString()
        var bruh = false
        BindingClass.ChangeButton.setOnClickListener()
        {
            BindingClass.changeButtonTemp.visibility = View.VISIBLE
            BindingClass.changeButtonTemp.alpha = 0f
            bruh = true
            BindingClass.changeButtonTemp.animate().apply{
                duration = 250
                alpha(1f)
            }
            BindingClass.textView13.animate().apply {
                duration = 200
                translationY(113f)
                setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        if (bruh){
                            BindingClass.textInputLayout7.visibility = View.VISIBLE
                            BindingClass.TextInputLayout8.visibility = View.VISIBLE
                            BindingClass.textInputLayout7.alpha = 0f
                            BindingClass.TextInputLayout8.alpha = 0f
                            BindingClass.textInputLayout7.animate().apply {
                                duration = 100
                                alpha(1f)
                            }
                            BindingClass.TextInputLayout8.animate().apply {
                                duration = 100
                                alpha(1f)
                                setListener(object : AnimatorListenerAdapter(){
                                    override fun onAnimationEnd(animation: Animator) {
                                        super.onAnimationEnd(animation)
                                        if (bruh){
                                            BindingClass.inpuiter.setText(BindingClass.textView12.text)
                                            BindingClass.inpuiter2.setText(BindingClass.textView13.text)
                                            BindingClass.textView13.visibility = View.GONE
                                            BindingClass.textView12.visibility = View.GONE
                                        }
                                    }
                                })
                            }
                        }

                    }
                })
            }
            BindingClass.textView12.animate().apply {
                duration = 200
                translationY(200f)
            }
            BindingClass.cardView9.animate().apply {
                duration = 150
                alpha(0f)
            }
            BindingClass.textView23.animate().apply {
                duration = 150
                alpha(0f)
            }
        }
        BindingClass.changeButtonTemp.setOnClickListener()
        {
            bruh = false

            BindingClass.textInputLayout7.animate().apply {
                duration = 100
                alpha(0f)
            }
            BindingClass.TextInputLayout8.animate().apply {
                duration = 100
                alpha(0f)
            }
            BindingClass.textView12.text = BindingClass.inpuiter.text.toString()
            BindingClass.textView13.text = BindingClass.inpuiter2.text.toString()
            BindingClass.inpuiter.setText("")
            BindingClass.inpuiter2.setText("")
            BindingClass.textView13.visibility = View.VISIBLE
            BindingClass.textView12.visibility = View.VISIBLE

            BindingClass.textView12.animate().apply {
                duration = 200
                translationY(0f)
            }
            BindingClass.textView13.animate().apply {
                duration = 200
                translationY(0f)
            }
            BindingClass.cardView9.animate().apply {
                duration = 150
                alpha(1f)
            }
            BindingClass.textView23.animate().apply {
                duration = 150
                alpha(1f)
            }
            BindingClass.changeButtonTemp.animate().apply{
                duration = 250
                alpha(0.0f)
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        if(bruh == false){
                            bruh = true
                            BindingClass.changeButtonTemp.visibility = View.GONE
                        }
                    }
                })
            }
        }
    }
}