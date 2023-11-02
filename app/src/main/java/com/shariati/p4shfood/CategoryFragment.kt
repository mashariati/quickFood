package com.shariati.p4shfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shariati.p4shfood.databinding.ActivityMainBinding
import com.shariati.p4shfood.databinding.FragmentCategoryBinding
import com.shariati.p4shfood.databinding.FragmentMenuBinding
import kotlin.math.log

class CategoryFragment(private val categoryCh:FragmentChanged) : Fragment(),CategoryAdapter.CategoryItemOnClick {
    lateinit var binding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        val categoryList: ArrayList<Category> = arrayListOf(
            Category(
                "Pizza",
                25,
                "https://www.uplooder.net/img/image/2/7c30e093fc57092a2e65f81239112e1f/11.png"
            ),
            Category ("Salads",
                30,
                "https://www.uplooder.net/img/image/76/3584f41330b90396480216c924905bf5/health-food-salad-vector-231093-261-prev-ui.png"
            ),
            Category ("Desserts",
                10,
                "https://www.uplooder.net/img/image/72/66fe583be5faf8414233736940531a29/top-up-view-cup-cake-strawberry-with-transparency-488442-82-prev-ui.png"
            )
            ,
            Category ("Pasta",
                4,
                "https://www.uplooder.net/img/image/28/9207e06d39125acd63b1d8115dc191c2/pasta-penne-italian-food-png.png"
            )
            ,
            Category ("Beverages",
                25,
"https://www.uplooder.net/img/image/66/621999a1f54c831322b196025aaa0d34/pngtree-coffee-coffee-cup-top-view-png-image-6695188.png"            ),
            Category ("Desserts",
                10,
                "https://www.uplooder.net/img/image/72/66fe583be5faf8414233736940531a29/top-up-view-cup-cake-strawberry-with-transparency-488442-82-prev-ui.png"
            )
            ,
            Category ("Pasta",
                4,
                "https://www.uplooder.net/img/image/28/9207e06d39125acd63b1d8115dc191c2/pasta-penne-italian-food-png.png"
            )

        )
        val categoryAdapter = CategoryAdapter(categoryList,this)
        binding.categoryRecyclerView.adapter = categoryAdapter
        binding.categoryRecyclerView.layoutManager  = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
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
        categoryCh.fragmentChanged("category")
    }
    override fun onCategoryItemOnclick(pos:Int) {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                transaction.replace(R.id.fragment_container,MenuFragment(categoryCh))
                transaction.addToBackStack(null)
                transaction.commit()
        //pass the position to menu fragment
        val bundle = Bundle()
        bundle.putInt("position",pos)
        parentFragmentManager.setFragmentResult("pos",bundle)

    }


}
