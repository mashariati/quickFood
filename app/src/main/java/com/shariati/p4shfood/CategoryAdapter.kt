package com.shariati.p4shfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoryAdapter(private val data: ArrayList<Category>,private val itemOnClick:CategoryItemOnClick) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(itemView: View,private val contex:Context) : RecyclerView.ViewHolder(itemView) {

        val categoryImage = itemView.findViewById<ImageView>(R.id.item_category_image)
        val categoryName = itemView.findViewById<TextView>(R.id.item_category_name)
        val categoryNumber = itemView.findViewById<TextView>(R.id.item_category_number)
        fun bindData(position: Int) {
            categoryName.text = data[position].categoryName
            categoryNumber.text = data[position].categoryNumber.toString() + " items"
            Glide.with(contex)
                .load(data[position].categoryImage)
                .into(categoryImage)

            itemView.setOnClickListener{
                itemOnClick.onCategoryItemOnclick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view,parent.context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindData(position)
    }
    interface CategoryItemOnClick{
        fun onCategoryItemOnclick(pos: Int)
    }
}