package com.shariati.p4shfood

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.shariati.p4shfood.databinding.ActivityMainBinding
import com.shariati.p4shfood.databinding.FragmentMenuBinding

class MainActivity : AppCompatActivity(), FragmentChanged {
    private lateinit var binding: ActivityMainBinding
    var isMenuBarEnable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //inflate activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //start category fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, CategoryFragment(this))
        transaction.commit()
        //click on the menu bar icon
        binding.menuBar.setOnClickListener {
            if(!isMenuBarEnable) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right,
                    R.anim.enter_right_to_left,
                    R.anim.exit_right_to_left
                )
                transaction.replace(R.id.fragment_container, CategoryFragment(this))
                transaction.commit()
            }
        }


    }

//Changing the color of the views when the fragment changes
    override fun fragmentChanged(frag: String) {
        when (frag) {
            "category" -> {
                binding.view8.setBackgroundResource(R.color.red)
                binding.view9.setBackgroundResource(R.color.white_dark)
                binding.view10.setBackgroundResource(R.color.white_dark)
                binding.menuBar.setImageResource(R.drawable.ic_menu_bar)
                isMenuBarEnable = true

            }

            "menu" -> {
                binding.view8.setBackgroundResource(R.color.red)
                binding.view9.setBackgroundResource(R.color.red)
                var animLeftToRight =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.enter_left_to_right)
                binding.view9.startAnimation(animLeftToRight)
                binding.view10.setBackgroundResource(R.color.white_dark)
                binding.menuBar.setImageResource(R.drawable.ic_arrow_left)
                isMenuBarEnable=false

            }
        }
    }

}