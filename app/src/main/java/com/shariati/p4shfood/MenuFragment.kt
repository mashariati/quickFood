package com.shariati.p4shfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
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
                    0->{
                        val pizzaMenuList:ArrayList<Menu> = arrayListOf(
                            Menu("Margherita","https://www.uplooder.net/img/image/57/e3601ebcab7893242cdd0368f070c163/product-beauty-the-margherita-1500x.png",3.5f,300,16.99f),
                            Menu("Garden fresh delight","https://www.uplooder.net/img/image/39/72b603fcabe5957b2fc84a7be1db8abb/1561098216975-gardenfreshdelight-m-prev-ui.png",4.5f,300,16.99f),
                            Menu("Paneer pizza","https://www.uplooder.net/img/image/70/1578d3f7004ff164957a2ec220c7b4ea/chatpata-paneer-pizza-prev-ui.png",4.2f,300,16.5f),
                            Menu("Bianca","https://www.uplooder.net/img/image/80/7cffa51e69e0f4d52ba1fd7f293af4eb/product-beauty-the-bianca-1445x.png",3.5f,300,22f),
                            Menu("Pepperoni","https://www.uplooder.net/img/image/82/f63ebfbec3310106227f10d16af7401c/product-beauty-the-pepperoni-750x.png",4.6f,300,24.99f),
                        )
                        binding.menuRecyclerView.adapter=MenuAdapter(pizzaMenuList)
                        binding.menuRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

                        binding.menuName.text="Pizza menu"
                    }
                    1->{
                        val saladsMenuList:ArrayList<Menu> = arrayListOf(
                            Menu("Aromatic Warm Thai Beef","https://www.uplooder.net/img/image/30/9307b30f904416d9d9265549d17ac5be/تصویر-صفحه-2023-11-04-182930-prev-ui.png",3.5f,300,16.99f),
                            Menu("Fragrant Vietnamese Chicken","https://www.uplooder.net/img/image/64/537d94834678c0b0dfee79c48304647f/تصویر-صفحه-2023-11-04-183140-prev-ui.png",4.5f,300,16.99f),
                            Menu("Moroccan Lamb","https://www.uplooder.net/img/image/50/5027ad2e27c6cde4f6e2770155ab517d/تصویر-صفحه-2023-11-04-183158-prev-ui.png",4.2f,300,16.5f),
                            Menu("Pepper Smoked Tassie Salmon","https://www.uplooder.net/img/image/30/72032bc1db6870e6b050960e184a2fdd/تصویر-صفحه-2023-11-04-183209-prev-ui.png",3.5f,300,22f),
                            Menu("Grilled Beef & Haloumi","https://www.uplooder.net/img/image/55/e10a9c2cba40813aed2885bdf25e258a/تصویر-صفحه-2023-11-04-183222-prev-ui.png",4.6f,300,24.99f),
                            Menu("Sumo's Famous Chicken Caesar","https://www.uplooder.net/img/image/26/a083ccf9b0c4aeb02512848bc733dd8a/تصویر-صفحه-2023-11-04-183233-prev-ui.png",4.6f,300,24.99f),
                        )
                        binding.menuRecyclerView.adapter=MenuAdapter(saladsMenuList)
                        binding.menuRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

                        binding.menuName.text="Salads menu"
                    }
                    2,5->{
                        val pizzaMenuList:ArrayList<Menu> = arrayListOf(
                            Menu("Margherita","https://www.uplooder.net/img/image/57/e3601ebcab7893242cdd0368f070c163/product-beauty-the-margherita-1500x.png",3.5f,300,16.99f),
                            Menu("Garden fresh delight","https://www.uplooder.net/img/image/39/72b603fcabe5957b2fc84a7be1db8abb/1561098216975-gardenfreshdelight-m-prev-ui.png",4.5f,300,16.99f),
                            Menu("Paneer pizza","https://www.uplooder.net/img/image/70/1578d3f7004ff164957a2ec220c7b4ea/chatpata-paneer-pizza-prev-ui.png",4.2f,300,16.5f),
                            Menu("Bianca","https://www.uplooder.net/img/image/80/7cffa51e69e0f4d52ba1fd7f293af4eb/product-beauty-the-bianca-1445x.png",3.5f,300,22f),
                            Menu("Pepperoni","https://www.uplooder.net/img/image/82/f63ebfbec3310106227f10d16af7401c/product-beauty-the-pepperoni-750x.png",4.6f,300,24.99f),
                        )
                        binding.menuRecyclerView.adapter=MenuAdapter(pizzaMenuList)
                        binding.menuRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

                        binding.menuName.text="Deserts menu"
                    }
                    3,6->{
                        val saladsMenuList:ArrayList<Menu> = arrayListOf(
                            Menu("Aromatic Warm Thai Beef","https://www.uplooder.net/img/image/30/9307b30f904416d9d9265549d17ac5be/تصویر-صفحه-2023-11-04-182930-prev-ui.png",3.5f,300,16.99f),
                            Menu("Fragrant Vietnamese Chicken","https://www.uplooder.net/img/image/64/537d94834678c0b0dfee79c48304647f/تصویر-صفحه-2023-11-04-183140-prev-ui.png",4.5f,300,16.99f),
                            Menu("Moroccan Lamb","https://www.uplooder.net/img/image/50/5027ad2e27c6cde4f6e2770155ab517d/تصویر-صفحه-2023-11-04-183158-prev-ui.png",4.2f,300,16.5f),
                            Menu("Pepper Smoked Tassie Salmon","https://www.uplooder.net/img/image/30/72032bc1db6870e6b050960e184a2fdd/تصویر-صفحه-2023-11-04-183209-prev-ui.png",3.5f,300,22f),
                            Menu("Grilled Beef & Haloumi","https://www.uplooder.net/img/image/55/e10a9c2cba40813aed2885bdf25e258a/تصویر-صفحه-2023-11-04-183222-prev-ui.png",4.6f,300,24.99f),
                            Menu("Sumo's Famous Chicken Caesar","https://www.uplooder.net/img/image/26/a083ccf9b0c4aeb02512848bc733dd8a/تصویر-صفحه-2023-11-04-183233-prev-ui.png",4.6f,300,24.99f),
                        )
                        binding.menuRecyclerView.adapter=MenuAdapter(saladsMenuList)
                        binding.menuRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

                        binding.menuName.text="Pasta menu"
                    }
                    4->{
                        val pizzaMenuList:ArrayList<Menu> = arrayListOf(
                            Menu("Margherita","https://www.uplooder.net/img/image/57/e3601ebcab7893242cdd0368f070c163/product-beauty-the-margherita-1500x.png",3.5f,300,16.99f),
                            Menu("Garden fresh delight","https://www.uplooder.net/img/image/39/72b603fcabe5957b2fc84a7be1db8abb/1561098216975-gardenfreshdelight-m-prev-ui.png",4.5f,300,16.99f),
                            Menu("Paneer pizza","https://www.uplooder.net/img/image/70/1578d3f7004ff164957a2ec220c7b4ea/chatpata-paneer-pizza-prev-ui.png",4.2f,300,16.5f),
                            Menu("Bianca","https://www.uplooder.net/img/image/80/7cffa51e69e0f4d52ba1fd7f293af4eb/product-beauty-the-bianca-1445x.png",3.5f,300,22f),
                            Menu("Pepperoni","https://www.uplooder.net/img/image/82/f63ebfbec3310106227f10d16af7401c/product-beauty-the-pepperoni-750x.png",4.6f,300,24.99f),
                        )
                        binding.menuRecyclerView.adapter=MenuAdapter(pizzaMenuList)
                        binding.menuRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

                        binding.menuName.text="Beverages menu"
                        binding.menuName.textSize= 33F
                    }

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