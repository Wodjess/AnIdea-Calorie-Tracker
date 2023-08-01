package com.example.anidea

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.anidea.databinding.ActivityAddnewfoodBinding
import androidx.appcompat.app.AlertDialog

object aga{
    var ImageUri: Uri? = null
}
class addnewfood : AppCompatActivity() {
    lateinit var BindingClass:ActivityAddnewfoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingClass = ActivityAddnewfoodBinding.inflate(layoutInflater)
        setContentView(BindingClass.root)

        BindingClass.imageView7.setImageResource(R.drawable.foodimg)
        lateinit var launch: ActivityResultLauncher<Intent>
        launch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if(result.resultCode.toString() == "-1"){
                aga.ImageUri = result.data?.data
                BindingClass.imageView7.setImageURI(aga.ImageUri)

            }
            //result.data?.getStringExtra("reg")

        }
        BindingClass.imageView7.setOnClickListener(){
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launch.launch(Intent.createChooser(intent, "Select Picture"))
        }
        var i = Intent()
        BindingClass.button3.setOnClickListener(){
            var isallgood = 0
            if(BindingClass.a1.text.toString().length >= 2){
                i.putExtra("NameInformation", BindingClass.a1.text.toString())
                if(aga.ImageUri != null){
                    i.putExtra("Image", aga.ImageUri)
                }
                isallgood++
            }
            if(BindingClass.a2.text.toString().length >= 2){
                try {
                    val test:Double = BindingClass.a2.text.toString().toDouble()
                    i.putExtra("CaloriesInformation", BindingClass.a2.text.toString())
                    isallgood++
                }
                catch (e:Exception){

                }
            }
            if(BindingClass.a3.text.toString().length >= 2){
                try {
                    val test:Double = BindingClass.a3.text.toString().toDouble()
                    i.putExtra("ProteinInformation", BindingClass.a3.text.toString())
                    isallgood++
                }
                catch (e:Exception){

                }

            }
            if(BindingClass.a4.text.toString().length >= 2){
                try {
                    val test:Double = BindingClass.a4.text.toString().toDouble()
                    i.putExtra("FatInformation", BindingClass.a4.text.toString())
                    isallgood++
                }
                catch (e:Exception){

                }

            }
            if(BindingClass.a5.text.toString().length >= 2){
                try {
                    val test:Double = BindingClass.a5.text.toString().toDouble()
                    i.putExtra("CarbInformation", BindingClass.a5.text.toString())
                    isallgood++
                }
                catch (e:Exception){

                }

            }
            if(isallgood == 5){
                setResult(100,i)
                finish()
            }
            else{
                val builder22 = AlertDialog.Builder(this)
                builder22.setTitle("Произошла ошибка")
                builder22.setMessage("Проверьте, чтобы все поля были заполнены и имели правильную информацию")
                builder22.setNeutralButton("Oк") { dialogInterface, i ->
                    
                }
                builder22.show()
            }

        }

    }
}