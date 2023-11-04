package com.shariati.p4shfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MenuAdapter(private val menuItemsList:ArrayList<Menu>):RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(itemView:View,private val context:Context):RecyclerView.ViewHolder(itemView){
        val menuImage=itemView.findViewById<ImageView>(R.id.item_menu_image)
        val menuName=itemView.findViewById<TextView>(R.id.item_menu_name)
        val menuRating=itemView.findViewById<RatingBar>(R.id.item_menu_rating)
        val menuWeight=itemView.findViewById<TextView>(R.id.item_menu_weight)
        val menuPrice=itemView.findViewById<TextView>(R.id.item_menu_price)

        fun bindMenuItem(position: Int){
            Glide.with(context)
                .load(menuItemsList[position].menuImage)
                .into(menuImage)
            menuName.text = menuItemsList[position].menuName
            menuRating.rating = menuItemsList[position].menuRating
            menuWeight.text = menuItemsList[position].menuWeight.toString()+"gr"
            menuPrice.text = menuItemsList[position].menuPrice.toString()+"$"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
            return MenuViewHolder(view,parent.context)
    }

    override fun getItemCount(): Int {
        return menuItemsList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
    holder.bindMenuItem(position)
    }
}