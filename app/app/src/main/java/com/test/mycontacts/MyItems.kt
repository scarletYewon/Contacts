package com.test.mycontacts

sealed class MyItems {
    data class SmItem(val aIcon: Int, val aName: String, val alike1: Int): MyItems()
    data class jypItem(val bIcon: Int, val bName: String, val blike2: Int): MyItems()
}
