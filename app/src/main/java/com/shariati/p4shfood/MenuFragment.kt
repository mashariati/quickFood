package com.shariati.p4shfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import com.shariati.p4shfood.databinding.FragmentCategoryBinding
import com.shariati.p4shfood.databinding.FragmentMenuBinding

class MenuFragment(private val menuCh:FragmentChanged) : Fragment() {
    lateinit var binding: FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get position from category fragment
        parentFragmentManager.setFragmentResultListener("pos",this,object:FragmentResultListener{
            override fun onFragmentResult(requestKey: String, result: Bundle) {
                val position = result.getInt("position")
                //set recycler view adapter
                when(position){

                }
            }

        })
        // Inflate the layout for this fragment
        return binding.root


    }


    override fun onStart() {
        super.onStart()
        menuCh.fragmentChanged("menu")


    }



}