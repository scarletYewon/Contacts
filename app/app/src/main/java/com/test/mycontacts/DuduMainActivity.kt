package com.test.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ex6_simplelistview.MyAdapter
import com.test.mycontacts.databinding.ActivityMainBinding

class DuduMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 원본 준비
        val dataList = mutableListOf(
            MyItems.SmItem(R.drawable.sm_taeyeon, "우윳빛깔 김태연", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_sunny, "사장님조카 써니", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_tiffany, "보석닮은 티파니", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_hyoyeon, "춤신춤왕 효연", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_haewon, "시원시원 해원", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_taeyeon, "재태크왕 유리", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_sullyoon, "설빙조아 설윤", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_sooyoung, "swimming 수영", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_bae, "베이비 배이", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_yoona, "얼굴천재 윤아", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_lily, "피카츄의왕 지우", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_kyujin, "귀엽다 규진", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_seohyun, "이쁜막내 서현", R.drawable.img_like)

        )
        // 어댑터 생성 및 연결
        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name: String = (dataList[position] as MyItems.SmItem).aName
                Toast.makeText(this@DuduMainActivity, " $name 선택!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}