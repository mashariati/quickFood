package com.shariati.p4shfood

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.shariati.p4shfood.databinding.ActivityMainBinding
import com.shariati.p4shfood.databinding.FragmentMenuBinding

class MainActivity : AppCompatActivity(), FragmentChanged{
    private lateinit var binding: ActivityMainBinding
    var isMenuBarEnable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //inflate activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set transparent status bar and navigation bar
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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
                supportFragmentManager.popBackStack()
                transaction.commit()


            }
        }


    }
    fun menuItemOnCLick(){

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

var cartItemNumber = 0
    fun onMenuAdd(menuModel: MenuModel) {
        if( binding.goToCart.visibility==View.GONE){
        binding.goToCart.visibility=View.VISIBLE
        binding.goToCartNumber.visibility=View.VISIBLE
            val alphaButton = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
            val scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(
                binding.goToCart,
                alphaButton
            )
            val scaleYAnimator2 = ObjectAnimator.ofPropertyValuesHolder(
                binding.goToCartNumber,
                alphaButton
            )
            scaleYAnimator.duration = 200
            scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()
            scaleYAnimator.start()
            scaleYAnimator2.duration = 200
            scaleYAnimator2.interpolator = AccelerateDecelerateInterpolator()
            scaleYAnimator2.start()
        binding.cartIcon.setColorFilter(ContextCompat.getColor(this,R.color.red),android.graphics.PorterDuff.Mode.SRC_IN)
        }
        cartItemNumber++
        binding.goToCartNumber.text=cartItemNumber.toString()


    }
}