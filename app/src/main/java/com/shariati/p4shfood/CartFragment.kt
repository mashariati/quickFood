package com.shariati.p4shfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shariati.p4shfood.databinding.FragmentCartBinding
import kotlin.math.log

class CartFragment(private val cartch: FragmentChanged) : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var getCartFragItems:MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartBinding.inflate(layoutInflater)
    BottomSheetBehavior.from(binding.cartSummary).apply {
        peekHeight= resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
        this.state=BottomSheetBehavior.STATE_COLLAPSED
    }
        getCartFragItems = context as MainActivity
        val cartAdapter=CartAdapter(getCartFragItems.cartItem)
        binding.cartRecyclerView.adapter=cartAdapter
        binding.cartRecyclerView.layoutManager=LinearLayoutManager(context, VERTICAL,false)

        //Enter the value in the summary
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
}