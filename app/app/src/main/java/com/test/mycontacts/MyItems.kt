package com.test.mycontacts

sealed class MyItems { // 동규 수정(4~5) : 전화번호,이메일 추가
    data class Item(val aIcon: Int, val aName: String, val aPhonenumber:String, val aEmail:String, val alike1: Int, val notifi:Int,var like:Int): MyItems(){
        var likeChanged = false

        fun toggleLike(): Boolean {
            val originalLikeStatus = this.like
            this.like = if (this.like == 0) 1 else 0
            likeChanged = originalLikeStatus != this.like
            return likeChanged
        }
    }
      companion object { // 동규 추가(6~24)
        var defaultDataList = mutableListOf<MyItems>(
            Item(R.drawable.sm_taeyeon, "Tae Yeon", "010-0000-0000", "taeyeon@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_sunny, "Sunny", "010-0000-0001", "sunny@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_tiffany, "Tiffany", "010-0000-0002", "tiffany@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_lily, "Lily ", "010-0000-0003", "lily@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_hyoyeon, "Hyo Yeon", "010-0000-0004", "hyoyeon@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_haewon, "Hae Won", "010-0000-0005", "haewon@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_yuri, "Yuri", "010-0000-0006", "yuri@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_sullyoon, "Sull Yoon", "010-0000-0007", "sullyoon@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_sooyoung, "Soo Young", "010-0000-0008", "sooyoung@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_bae, "Bae", "010-0000-0009", "bae@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_lily, "Lily", "010-0000-0010", "lily@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_yoona, "Yoon Ah", "010-0000-0011", "yoona@sm.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_jiwoo, "Ji Woo", "010-0000-0012", "jiwoo@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.jyp_kyujin, "Kyu Jin", "010-0000-0013", "kyujin@jyp.kr", R.drawable.img_like3,0,0),
            Item(R.drawable.sm_seohyun, "Seo Hyun", "010-0000-0014", "seohyun@sm.kr", R.drawable.img_like3,0,0)
        )
    }
}