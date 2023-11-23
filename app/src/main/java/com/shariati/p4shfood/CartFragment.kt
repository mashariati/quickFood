package com.shariati.p4shfood

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shariati.p4shfood.databinding.DialogCartRemoveItemBinding
import com.shariati.p4shfood.databinding.FragmentCartBinding

class CartFragment(private val cartch: FragmentChanged) : Fragment(),CartAdapter.CartEvents {
    lateinit var binding: FragmentCartBinding
    lateinit var getCartFragItems:MainActivity
    lateinit var cartAdapter:CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartBinding.inflate(layoutInflater)
    BottomSheetBehavior.from(binding.cartSummary).apply {
        peekHeight= resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
        this.state=BottomSheetBehavior.STATE_COLLAPSED
    }
        getCartFragItems = context as MainActivity
        cartAdapter=CartAdapter(getCartFragItems.cartItem,this)
        binding.cartRecyclerView.adapter=cartAdapter
        binding.cartRecyclerView.layoutManager=LinearLayoutManager(context, VERTICAL,false)

        fillTheSummary()

    }
    //Enter the value in the summary
    private fun fillTheSummary(){
        var subTotal = 0f
        var numberOfItems = 0
        getCartFragItems.cartItem.forEach {
            subTotal+=(it.cartPrice)*it.cartNumber
            numberOfItems+=it.cartNumber
        }
        val tax = (subTotal*.09)
        binding.itemMenuPItems.text = subTotal.toString()
        binding.itemMenuTItems.text = tax.toString().substring(0,6)
        binding.itemMenuTpItems.text=(subTotal+tax).toString().substring(0,6)
        binding.itemMenuNItems.text=numberOfItems.toString()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        cartch.fragmentChanged("cart")
    }

    //remove an item from the Cart fragment
    override fun removeItem(cart: Cart, position: Int) {
        //show the dialog when you long click to an item
        val dialog = AlertDialog.Builder(context).create()
        val dialogView = DialogCartRemoveItemBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialog.setCancelable(true)
        dialog.show()
        //remove or cancel remove the item
        dialogView.dialogNo.setOnClickListener {
            dialog.dismiss()
        }
        dialogView.dialogYes.setOnClickListener {
            cartAdapter.removeItem(cart,position)
            cart.cartNumber=1
            if(getCartFragItems.cartItem.size==0){
                val transaction = parentFragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                parentFragmentManager.popBackStack()
                transaction.replace(R.id.fragment_container,CategoryFragment(cartch))
                parentFragmentManager.popBackStack()
                transaction.commit()
                //set the cart icon number text with a function in mainactivity
               getCartFragItems.setCartIconNumberText(false)
            }else{
                fillTheSummary()
                getCartFragItems.setCartIconNumberText(true)
            }
            dialog.dismiss()
        }

    }

    //reduce a number of Item
    override fun reduceNOfI(cart: Cart, position: Int, itemView: View) {
        var isMinusEnable:Boolean =
            itemView.findViewById<TextView>(R.id.item_cart_number).text.toString().toInt()>1
        if(isMinusEnable){
            cartAdapter.reduceItem(cart,position)
            fillTheSummary()
        }else{
            Toast.makeText(context, "please long click to remove the item", Toast.LENGTH_SHORT).show()
        }
    }
//add a number to item in cart
    override fun addNToI(cart: Cart, position: Int, itemView: View) {
        itemView.findViewById<ImageView>(R.id.item_cart_minus).setColorFilter(
                ContextCompat.getColor(getCartFragItems, R.color.red))
        cartAdapter.addItem(cart,position)
        fillTheSummary()

    }
}