package com.test.mycontacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ex6_simplelistview.MyAdapter
import com.test.mycontacts.databinding.DialogBinding
import com.test.mycontacts.databinding.FragmentDuduBinding

class ContactList : Fragment() {

    private lateinit var binding: FragmentDuduBinding
    private val dataList = mutableListOf<MyItems>()


    override fun onCreateView(
        inflater:LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?
    ):View? {

        binding = FragmentDuduBinding.inflate(inflater,container,false)

        // 데이터 원본 준비
         dataList.addAll(
             listOf(
            MyItems.SmItem(R.drawable.sm_taeyeon, "우윳빛깔 김태연", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_sunny, "사장님조카 써니", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_tiffany, "보석닮은 티파니", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_hyoyeon, "춤신춤왕 효연", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_haewon, "시원시원 해원", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_yuri, "재태크왕 유리", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_sullyoon, "설빙조아 설윤", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_sooyoung, "swimming 수영", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_bae, "베이비 배이", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_yoona, "얼굴천재 윤아", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_jiwoo, "피카츄의왕 지우", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_kyujin, "귀엽다 규진", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_seohyun, "이쁜막내 서현", R.drawable.img_like)

        )
         )
        // 어댑터 생성 및 연결
        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name: String = (dataList[position] as MyItems.SmItem).aName
                Toast.makeText(requireContext(), " $name 선택!", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root


    }
    fun addContact(name:String)
    {
        val addContact = MyItems.SmItem(R.drawable.basic,name,R.drawable.img_like)
        dataList.add(addContact)

        Log.d("ContactList", "dataList: $dataList")
    }


}