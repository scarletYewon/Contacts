package com.test.mycontacts

sealed class MyItems { // 동규 수정(4~5) : 전화번호,이메일 추가
    data class SmItem(val aIcon: Int, val aName: String, val aPhonenumber:String, val aEmail:String, val alike1: Int, val notifi:Int): MyItems()
    data class jypItem(val bIcon: Int, val bName: String, val bPhonenumber:String, val bEmail:String, val blike2: Int, val notifi:Int): MyItems()
    companion object { // 동규 추가(6~24)
        var defaultDataList = mutableListOf<MyItems>(
            SmItem(R.drawable.sm_taeyeon, "우윳빛깔 김태연", "010-0000-0000", "taeyeon@sm.kr", R.drawable.img_like3,0),
            SmItem(R.drawable.sm_sunny, "사장님조카 써니", "010-0000-0001", "sunny@sm.kr", R.drawable.img_like3,0),
            SmItem(R.drawable.sm_tiffany, "보석닮은 티파니", "010-0000-0002", "tiffany@sm.kr", R.drawable.img_like3,0),
            jypItem(R.drawable.jyp_lily, "요정 릴리", "010-0000-0003", "lily@jyp.kr", R.drawable.img_like2,0),
            SmItem(R.drawable.sm_hyoyeon, "춤신춤왕 효연", "010-0000-0004", "hyoyeon@sm.kr", R.drawable.img_like3,0),
            jypItem(R.drawable.jyp_haewon, "시원시원 해원", "010-0000-0005", "haewon@jyp.kr", R.drawable.img_like2,0),
            SmItem(R.drawable.sm_yuri, "재태크왕 유리", "010-0000-0006", "yuri@sm.kr", R.drawable.img_like3,0),
            jypItem(R.drawable.jyp_sullyoon, "설빙조아 설윤", "010-0000-0007", "sullyoon@jyp.kr", R.drawable.img_like2,0),
            SmItem(R.drawable.sm_sooyoung, "swimming 수영", "010-0000-0008", "sooyoung@sm.kr", R.drawable.img_like3,0),
            jypItem(R.drawable.jyp_bae, "베이비 배이", "010-0000-0009", "bae@jyp.kr", R.drawable.img_like2,0),
            jypItem(R.drawable.jyp_lily, "요정 릴리", "010-0000-0010", "lily@jyp.kr", R.drawable.img_like2,0),
            SmItem(R.drawable.sm_yoona, "얼굴천재 윤아", "010-0000-0011", "yoona@sm.kr", R.drawable.img_like3,0),
            jypItem(R.drawable.jyp_jiwoo, "피카츄의왕 지우", "010-0000-0012", "jiwoo@jyp.kr", R.drawable.img_like2,0),
            jypItem(R.drawable.jyp_kyujin, "귀엽다 규진", "010-0000-0013", "kyujin@jyp.kr", R.drawable.img_like2,0),
            SmItem(R.drawable.sm_seohyun, "이쁜막내 서현", "010-0000-0014", "seohyun@sm.kr", R.drawable.img_like3,0)
        )
    }
}