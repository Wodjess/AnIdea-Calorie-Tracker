package com.example.anidea

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anidea.databinding.RecyclerviewBinding
import com.example.anidea.size.adapter
import com.example.anidea.size.adaptery
import com.example.anidea.size.adapterz
import java.lang.Exception
import java.util.*

data class Model(val name:String, val v:String, val calories:String,val proteins:String,val fats:String,val carbs:String,val whereFood:Int, val image:Uri?, val Position:Int = (Int.MIN_VALUE..Int.MAX_VALUE).random()){

}
object size{
    var adapter = ModelAdapter()
    var adapterz = ModelAdapter()
    var adaptery = ModelAdapter()

}
class ModelAdapter : RecyclerView.Adapter<ModelAdapter.ModelPlan>()
{
    //нужно создать xaml разметку с тем эллементом, который мы хотим юзать
    val listOfModel = ArrayList<Model>()

    class ModelPlan(item: View) :RecyclerView.ViewHolder(item) {
        val binding = RecyclerviewBinding.bind(item)
        fun bind(model: Model){
            binding.textView7.text = model.name + "    " + model.v
            binding.textView5.text = ((model.v.toString().toDouble() / 100).toDouble() * model.calories.toString().toDouble()).toString()
            binding.imageView.tag = model.Position
            binding.imageView.setOnClickListener(){
                    when(model.whereFood){
                        0 -> adapter.delNote(binding.imageView.tag as Int)
                        1 -> adaptery.delNote(binding.imageView.tag as Int)
                        2 -> adapterz.delNote(binding.imageView.tag as Int)
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelPlan {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return ModelPlan(view)
    }

    override fun getItemCount(): Int {
        return listOfModel.size
    }

    override fun onBindViewHolder(holder: ModelPlan, position: Int) {
        holder.bind(listOfModel[position])
    }
    fun addNote(model: Model){
        listOfModel.add(model)
        notifyDataSetChanged()
    }
    fun delNote(model: Int){
        try {
            var result = listOfModel.indexOfLast { it.Position  == model}
            var resultmodel: Model? = listOfModel.find { it.Position == model }
            listOfModel.removeAt(result)
            if(resultmodel != null){
                DetailInformation.allcalories -= ((resultmodel?.v.toString().toDouble() / 100).toDouble() * resultmodel?.calories.toString().toDouble())
                DetailInformation.allproteins -= ((resultmodel?.v.toString().toDouble() / 100).toDouble() * resultmodel?.proteins.toString().toDouble())
                DetailInformation.allfats -= ((resultmodel?.v.toString().toDouble() / 100).toDouble() * resultmodel?.fats.toString().toDouble())
                DetailInformation.allcarbs -= ((resultmodel?.v.toString().toDouble() / 100).toDouble() * resultmodel?.carbs.toString().toDouble())
                DetailInformation.ischanged = true
            }
            notifyItemRemoved(result)
        }
        catch (e:Exception){ }
    }
}