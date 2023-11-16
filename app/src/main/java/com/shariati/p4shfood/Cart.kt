package com.shariati.p4shfood

data class Cart(
    val cartName:String,
    val cartImage:String,
    val cartRating:Float,
    val cartWeight:Int,
    val cartPrice:Float,
    var cartNumber: Int
)
