package com.test.mycontacts

sealed class MyItems {
    data class SmItem(val aIcon: Int, val aName: String, val alike: Int): MyItems()
    data class jypItem(val bIcon: Int, val bName: String, val blike: Int): MyItems()
}
