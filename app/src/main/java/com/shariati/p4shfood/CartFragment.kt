package com.shariati.p4shfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shariati.p4shfood.databinding.FragmentCartBinding

class CartFragment(private val cartch: FragmentChanged) : Fragment() {
    lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCartBinding.inflate(layoutInflater)
    BottomSheetBehavior.from(binding.cartSummary).apply {
        peekHeight= resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._60sdp)
        this.state=BottomSheetBehavior.STATE_COLLAPSED
    }
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