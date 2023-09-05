package com.test.mycontacts

sealed class MyItems { // 전화번호,이메일 추가
    data class SmItem(val aIcon: Int, val aName: String, val aPhonenumber:String, val aEmail:String, val alike1: Int): MyItems()
    data class jypItem(val bIcon: Int, val bName: String, val bPhonenumber:String, val bEmail:String, val blike2: Int): MyItems()
}
