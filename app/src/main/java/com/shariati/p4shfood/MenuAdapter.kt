package com.shariati.p4shfood

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MenuAdapter(private val menuItemsList:ArrayList<Menu>):RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(itemView:View,private val context:Context):RecyclerView.ViewHolder(itemView){
        val menuImage=itemView.findViewById<ImageView>(R.id.item_menu_image)
        val menuName=itemView.findViewById<TextView>(R.id.item_menu_name)
        val menuRating=itemView.findViewById<RatingBar>(R.id.item_menu_rating)
        val menuWeight=itemView.findViewById<TextView>(R.id.item_menu_weight)
        val menuPrice=itemView.findViewById<TextView>(R.id.item_menu_price)
        val menuDetails=itemView.findViewById<TextView>(R.id.item_menu_details_text)

        fun bindMenuItem(position: Int, holder: MenuViewHolder){
            Glide.with(context)
                .load(menuItemsList[position].menuImage)
                .into(menuImage)
            menuName.text = menuItemsList[position].menuName
            menuRating.rating = menuItemsList[position].menuRating
            menuWeight.text = menuItemsList[position].menuWeight.toString()+"gr"
            menuPrice.text = menuItemsList[position].menuPrice.toString()+"$"
            menuDetails.text=menuItemsList[position].menuDetails

            var menuModel = Cart(
                menuItemsList[position].menuName,
                menuItemsList[position].menuImage,
                menuItemsList[position].menuRating,
                menuItemsList[position].menuWeight,
                menuItemsList[position].menuPrice,
                1
            )
            itemView.findViewById<ImageView>(R.id.item_menu_add).setOnClickListener {
                val mainActivity = context as MainActivity
                mainActivity.onMenuAdd(menuModel)

            }
            itemView.setOnClickListener{
                if(holder.itemView.findViewById<FrameLayout>(R.id.item_menu_details).visibility==View.GONE) {
                    holder.itemView.findViewById<FrameLayout>(R.id.item_menu_details).visibility =
                        View.VISIBLE
                    val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
                    val scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(
                        holder.itemView.findViewById<FrameLayout>(R.id.item_menu_details),
                        alphaHolder
                    )
                    scaleYAnimator.duration = 1000
                    scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()
                    scaleYAnimator.start()

                }
                else{
                    holder.itemView.findViewById<FrameLayout>(R.id.item_menu_details).visibility =
                        View.GONE

                }
            }
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
    holder.bindMenuItem(position,holder)


    }

}
