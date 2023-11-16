package com.shariati.p4shfood

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shariati.p4shfood.databinding.ActivityMainBinding
import com.shariati.p4shfood.databinding.FragmentMenuBinding

class MainActivity : AppCompatActivity(), FragmentChanged{
    private lateinit var binding: ActivityMainBinding
    var isMenuBarEnable = true
    var isCartFull = false
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
        //go to cart fragment
        binding.goToCart.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            transaction.replace(R.id.fragment_container,CartFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()

            val alphaButton = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
            val scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(
                binding.cartIcon,
                alphaButton
            )
            val scaleYAnimator2 = ObjectAnimator.ofPropertyValuesHolder(
                binding.goToCartNumber,
                alphaButton
            )
            scaleYAnimator.duration = 600
            scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()
            scaleYAnimator.start()
            scaleYAnimator2.duration = 600
            scaleYAnimator2.interpolator = AccelerateDecelerateInterpolator()
            scaleYAnimator2.start()
            binding.cartIcon.setColorFilter(ContextCompat.getColor(this,R.color.white),android.graphics.PorterDuff.Mode.SRC_IN)
            binding.goToCart.visibility=View.GONE
            binding.goToCartNumber.backgroundTintList = ColorStateList.valueOf(getColor(R.color.white))
            binding.goToCartNumber.setTextColor(getColor(R.color.red))
            isCartFull=false
        }
        binding.cartIcon.setOnClickListener {
            if(isCartFull){
                val transaction2 = supportFragmentManager.beginTransaction()
                transaction2.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                transaction2.replace(R.id.fragment_container,CartFragment(this))
                transaction2.addToBackStack(null)
                transaction2.commit()

                val alphaButton = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
                val scaleYAnimator = ObjectAnimator.ofPropertyValuesHolder(
                    binding.cartIcon,
                    alphaButton
                )
                val scaleYAnimator2 = ObjectAnimator.ofPropertyValuesHolder(
                    binding.goToCartNumber,
                    alphaButton
                )
                scaleYAnimator.duration = 600
                scaleYAnimator.interpolator = AccelerateDecelerateInterpolator()
                scaleYAnimator.start()
                scaleYAnimator2.duration = 600
                scaleYAnimator2.interpolator = AccelerateDecelerateInterpolator()
                scaleYAnimator2.start()

                binding.cartIcon.setColorFilter(ContextCompat.getColor(this,R.color.white),android.graphics.PorterDuff.Mode.SRC_IN)
                binding.goToCart.visibility=View.GONE
                binding.goToCartNumber.backgroundTintList = ColorStateList.valueOf(getColor(R.color.white))
                binding.goToCartNumber.setTextColor(getColor(R.color.red))

                isCartFull=false
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
                if(cartItemNumber>0) {
                    binding.cartIcon.setColorFilter(
                        ContextCompat.getColor(this, R.color.red),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    binding.goToCart.visibility = View.VISIBLE
                    binding.goToCartNumber.backgroundTintList =
                        ColorStateList.valueOf(getColor(R.color.red))
                    binding.goToCartNumber.setTextColor(getColor(R.color.white))
                    isCartFull=true
                }
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
                if(cartItemNumber>0) {
                    binding.cartIcon.setColorFilter(
                        ContextCompat.getColor(this, R.color.red),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    binding.goToCart.visibility = View.VISIBLE
                    binding.goToCartNumber.backgroundTintList =
                        ColorStateList.valueOf(getColor(R.color.red))
                    binding.goToCartNumber.setTextColor(getColor(R.color.white))
                    isCartFull=true
                }
            }
            "cart" -> {
                binding.view8.setBackgroundResource(R.color.red)
                binding.view9.setBackgroundResource(R.color.red)
                binding.view10.setBackgroundResource(R.color.red)
                var animLeftToRight =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.enter_left_to_right)
                binding.view10.startAnimation(animLeftToRight)
                binding.menuBar.setImageResource(R.drawable.ic_menu_bar)
                isMenuBarEnable=true

            }
        }
    }
//click to the add button in menu fragment
var cartItemNumber = 0
     var cartItem:ArrayList<Cart> = arrayListOf()
    fun onMenuAdd(cartModel: Cart) {
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
            isCartFull=true
        }


        //add item to cartItem array
        var doesHaveAnInstance=false
cartItem.forEach {
    if(it.cartName==cartModel.cartName){
       it.cartNumber +=1
        doesHaveAnInstance=true
    }
}
        if(!doesHaveAnInstance){
            cartItem.add(cartModel)
            cartItemNumber = cartItem.size
            binding.goToCartNumber.text=cartItemNumber.toString()
}

    }
//removed an item from cart when you click to minus button
fun minusItem(item:Cart){
    cartItem.forEach {
        if(it.cartName==item.cartName){
            it.cartNumber--

        }

    }
    }

}