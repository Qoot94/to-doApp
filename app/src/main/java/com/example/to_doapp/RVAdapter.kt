package com.example.to_doapp


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

//
//interface itemClickListener {
//    fun onClick(position: Int)
//}

class TodoList(name: String) {
    var name = name
    var isFilled: Boolean = false

}

class RVAdapter(var todoList: ArrayList<TodoList>) :
    RecyclerView.Adapter<RVAdapter.itemListHolder>() {
    class itemListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemListHolder {
        return itemListHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )

    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: itemListHolder, position: Int) {
        val item = todoList[position]
        holder.itemView.apply {
            tvItem.text = item.name
            //checkBox.isChecked = item.isFilled
            checkBox.setOnClickListener {
                //item.isFilled = checkBox.isChecked
                when (checkBox.isChecked) {
                    true -> {
                        item.isFilled = true
                        tvItem.setTextColor(Color.parseColor("#A63B0F"))

                    }
                    false -> {
                        item.isFilled = false
                        tvItem.setTextColor(Color.parseColor("#232D42"))
                    }
                }
            }
            tvItem.setTextColor(Color.parseColor("#232D42"))
            checkBox.isChecked = false
        }
    }

    override fun getItemCount(): Int = todoList.size
}