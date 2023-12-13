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
import com.shariati.p4shfood.databinding.ItemMenuBinding

class MenuAdapter(private val menuItemsList:ArrayList<Menu>):RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(private val binding: ItemMenuBinding ,private val context:Context):RecyclerView.ViewHolder(binding.root){


        fun bindMenuItem(position: Int){
            Glide.with(context)
                .load(menuItemsList[position].menuImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemMenuImage)
            binding.itemMenuName.text = menuItemsList[position].menuName
            binding.itemMenuRating.rating = menuItemsList[position].menuRating
            binding.itemMenuWeight.text = menuItemsList[position].menuWeight.toString()+"gr"
            binding.itemMenuPrice.text = menuItemsList[position].menuPrice.toString()+"$"
            binding.itemMenuDetailsText.text=menuItemsList[position].menuDetails

            var menuModel = Cart(
                menuItemsList[position].menuName,
                menuItemsList[position].menuImage,
                menuItemsList[position].menuRating,
                menuItemsList[position].menuWeight,
                menuItemsList[position].menuPrice,
                1
            )
            binding.itemMenuAdd.setOnClickListener {
                //call onMenuAdd method in activity for add an item to cart
                val mainActivity = context as MainActivity
                mainActivity.onMenuAdd(menuModel)

            }
            //show description when clicked an item
            binding.root.setOnClickListener{
                if(binding.itemMenuDetails.visibility==View.GONE) {
                    binding.itemMenuDetails.visibility =
                        View.VISIBLE
                    val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
                    val scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(
                        binding.itemMenuDetails,
                        alphaHolder
                    )
                    scaleYAnimator.duration = 1000
                    scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()
                    scaleYAnimator.start()

                }
                else{
                    binding.itemMenuDetails.visibility =
                        View.GONE

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        //create view binding for adapter
       val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding,parent.context)
    }

    override fun getItemCount(): Int {
        return menuItemsList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
    holder.bindMenuItem(position)


    }

}
