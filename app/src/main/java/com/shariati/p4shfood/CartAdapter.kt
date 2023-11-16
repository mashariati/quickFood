package com.shariati.p4shfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(private val cartItemList: ArrayList<Cart>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        val cartName = itemView.findViewById<TextView>(R.id.item_cart_name)
        val cartImage = itemView.findViewById<ImageView>(R.id.item_cart_image)
        val cartRaiting = itemView.findViewById<RatingBar>(R.id.item_cart_rating)
        val cartWeight = itemView.findViewById<TextView>(R.id.item_cart_weight)
        val cartPrice = itemView.findViewById<TextView>(R.id.item_cart_price)
        val cartNumber = itemView.findViewById<TextView>(R.id.item_cart_number)
        fun bindCartItem(position: Int) {
            cartName.text = cartItemList[position].cartName
            cartRaiting.rating = cartItemList[position].cartRating
            cartWeight.text = cartItemList[position].cartWeight.toString() + "gr"
            cartPrice.text = cartItemList[position].cartPrice.toString() + "$"
            cartNumber.text = cartItemList[position].cartNumber.toString()
            Glide.with(context)
                .load(cartItemList[position].cartImage)
                .into(cartImage)

            //minus item
            var isMinusEnable = false
            if (itemView.findViewById<TextView>(R.id.item_cart_number).text != "1") {
                itemView.findViewById<ImageView>(R.id.item_cart_minus)
                    .setColorFilter(ContextCompat.getColor(context, R.color.red))
                isMinusEnable = true
            }

            itemView.findViewById<ImageView>(R.id.item_cart_minus).setOnClickListener {
                val mainAc = context as MainActivity
                if (isMinusEnable) {
                    mainAc.minusItem(cartItemList[position])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindCartItem(position)
    }
}