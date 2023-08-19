package com.example.anidea

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.anidea.databinding.ActivityHowmuchgramsBinding


class howmuchgrams : AppCompatActivity() {
    lateinit var BindingClass: ActivityHowmuchgramsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingClass = ActivityHowmuchgramsBinding.inflate(layoutInflater)
        setContentView(BindingClass.root)

        BindingClass.textView4.text = intent.getStringExtra("Name")
        BindingClass.textView16.text = "Всего каллорий: " + intent.getStringExtra("calories")
        BindingClass.textView17.text = intent.getStringExtra("carb")
        BindingClass.textView18.text = "Ж: " + intent.getStringExtra("fat")
        BindingClass.textView22.text = "Всего каллорий: " + intent.getStringExtra("potein")

        try {
            BindingClass.imageView61.setImageResource(R.drawable.foodimg)
        }
        catch (e:Exception) {
            Log.d("LeshkaKartoshka", e.toString())
        }
        BindingClass.button4.setOnClickListener(){
            val i = Intent()
            if(BindingClass?.howmuchgramsstring?.text.toString().isNotEmpty()){
                try {
                    var Abs: Double = 0.toDouble()
                    Abs = BindingClass?.howmuchgramsstring?.text.toString().toDouble()
                    i.putExtra("export", BindingClass?.howmuchgramsstring?.text.toString())
                    setResult(100,i)
                    finish()
                }
                catch (e:java.lang.Exception){
                    val builder22 = AlertDialog.Builder(this)
                    builder22.setTitle("Произошла ошибка")
                    builder22.setMessage("Проверьте данные")
                    builder22.setNeutralButton("Oк") { dialogInterface, i ->

                    }
                    builder22.show()
                }
            }
        }
    }
}