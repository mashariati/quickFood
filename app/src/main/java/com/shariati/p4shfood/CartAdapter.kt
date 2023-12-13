package com.shariati.p4shfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shariati.p4shfood.databinding.ItemCartBinding

class CartAdapter(private val cartItemList: ArrayList<Cart>, private val events: CartEvents) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(private val binding: ItemCartBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCartItem(position: Int) {
            binding.itemCartName.text = cartItemList[position].cartName
            binding.itemCartRating.rating = cartItemList[position].cartRating
            binding.itemCartWeight.text = cartItemList[position].cartWeight.toString() + "gr"
            binding.itemCartPrice.text = cartItemList[position].cartPrice.toString() + "$"
            binding.itemCartNumber.text = cartItemList[position].cartNumber.toString()
            Glide.with(context)
                .load(cartItemList[position].cartImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemCartImage)


            //reduce the number of item if you click to minus button
            if (binding.itemCartNumber.text.toString()
                    .toInt() > 1
            ) {
                binding.itemCartMinus.setColorFilter(
                    ContextCompat.getColor(context, R.color.red)
                )
            } else {
                binding.itemCartMinus.setColorFilter(
                    ContextCompat.getColor(context, R.color.cart_color)
                )
            }
            binding.itemCartMinus.setOnClickListener {

                events.reduceNOfI(cartItemList[adapterPosition], adapterPosition,binding.root)
            }

            //add the number of item if you click to minus button

            binding.itemCartAdd.setOnClickListener {

                events.addNToI(cartItemList[adapterPosition], adapterPosition, binding.root)
            }

            //remove an item from the cartList
           binding.root.setOnLongClickListener {

                events.removeItem(cartItemList[adapterPosition], adapterPosition)

                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindCartItem(position)
    }

    interface CartEvents {
        fun removeItem(cart: Cart, position: Int)
        fun reduceNOfI(cart: Cart, position: Int, itemView: View)
        fun addNToI(cart: Cart, position: Int, itemView: View)
    }

    fun removeItem(cart: Cart, position: Int) {
        cartItemList.remove(cart)
        notifyItemRemoved(position)
    }

    fun reduceItem(cart: Cart, position: Int) {
        cart.cartNumber--
        notifyItemChanged(position)
    }

    fun addItem(cart: Cart, position: Int) {
        cart.cartNumber++
        notifyItemChanged(position)
    }
}