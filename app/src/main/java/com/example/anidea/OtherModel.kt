package com.example.anidea

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.anidea.databinding.RecycledviewoffoodsBinding
import java.net.BindException
import kotlin.math.roundToInt

data class ModelFood(val name:String?, val Calories:String?, val value:String?, val Fat:Double?, val Protein:Double?, val carb:Double?,val image:Uri?, val id:Int = (Int.MIN_VALUE..Int.MAX_VALUE).random()){

}
object newFoodMemory{
    var FoodTemp:ModelFood? = null
}
class ModelFoodAdapter : RecyclerView.Adapter<ModelFoodAdapter.ModelPlan>()
{

    val listOfModel = ArrayList<ModelFood>()

    class ModelPlan(item: View) :RecyclerView.ViewHolder(item) {
        val binding = RecycledviewoffoodsBinding.bind(item)
        fun bind(model: ModelFood){
            binding.textView8.text = model.name
            binding.textView14.text = model.value
            binding.textView15.text = model.Protein?.roundToInt().toString() + " ккал"
            binding.MainCardView.setOnClickListener(){
                newFoodMemory.FoodTemp = model
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelPlan {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycledviewoffoods, parent, false)
        return ModelPlan(view)
    }

    override fun getItemCount(): Int {
        return listOfModel.size
    }

    override fun onBindViewHolder(holder: ModelPlan, position: Int) {
        holder.bind(listOfModel[position])
    }
    fun addNote(model: ModelFood){
        listOfModel.add(model)
        notifyDataSetChanged()
    }
    fun delNote(model: ModelFood)
    {
        val result = listOfModel.indexOfLast { it.id  == model.id}
        listOfModel.removeAt(result)
        notifyItemRemoved(result)
    }
    fun delNote(model: Int)
    {
        val result = listOfModel.indexOfLast { it.id  == model}
        listOfModel.removeAt(result)
        notifyItemRemoved(result)
    }

}