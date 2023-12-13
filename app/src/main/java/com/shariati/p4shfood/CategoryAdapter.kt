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
import com.shariati.p4shfood.databinding.ItemCategoryBinding

class CategoryAdapter(private val data: ArrayList<Category>,private val itemOnClick:CategoryItemOnClick) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(private val binding: ItemCategoryBinding,private val context:Context) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(position: Int) {
            binding.itemCategoryName.text = data[position].categoryName
            binding.itemCategoryNumber.text = data[position].categoryNumber.toString() + " items"
            Glide.with(context)
                .load(data[position].categoryImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemCategoryImage)

            itemView.setOnClickListener{
                itemOnClick.onCategoryItemOnclick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
//create view binding for adapter
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CategoryViewHolder(binding,parent.context)
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