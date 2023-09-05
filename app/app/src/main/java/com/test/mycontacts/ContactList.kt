package com.test.mycontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ex6_simplelistview.MyAdapter
import com.test.mycontacts.databinding.FragmentDuduBinding

class ContactList : Fragment() {

    private lateinit var binding: FragmentDuduBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDuduBinding.inflate(inflater, container, false)

        // 데이터 원본 준비 // 전화번호,이메일 추가
        val dataList = mutableListOf(
            MyItems.SmItem(R.drawable.sm_taeyeon, "우윳빛깔 김태연", "010-0000-0000", "taeyeon@sm.kr", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_sunny, "사장님조카 써니", "010-0000-0001", "sunny@sm.kr", R.drawable.img_like),
            MyItems.SmItem(R.drawable.sm_tiffany, "보석닮은 티파니", "010-0000-0002", "tiffany@sm.kr", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", "010-0000-0003", "lily@jyp.kr", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_hyoyeon, "춤신춤왕 효연", "010-0000-0004", "hyoyeon@sm.kr", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_haewon, "시원시원 해원", "010-0000-0005", "haewon@jyp.kr", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_yuri, "재태크왕 유리", "010-0000-0006", "yuri@sm.kr", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_sullyoon, "설빙조아 설윤", "010-0000-0007", "sullyoon@jyp.kr", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_sooyoung, "swimming 수영", "010-0000-0008", "sooyoung@sm.kr", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_bae, "베이비 배이", "010-0000-0009", "bae@jyp.kr", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_lily, "요정 릴리", "010-0000-0010", "lily@jyp.kr", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_yoona, "얼굴천재 윤아", "010-0000-0011", "yoona@sm.kr", R.drawable.img_like),
            MyItems.jypItem(R.drawable.jyp_jiwoo, "피카츄의왕 지우", "010-0000-0012", "jiwoo@jyp.kr", R.drawable.img_like2),
            MyItems.jypItem(R.drawable.jyp_kyujin, "귀엽다 규진", "010-0000-0013", "kyujin@jyp.kr", R.drawable.img_like2),
            MyItems.SmItem(R.drawable.sm_seohyun, "이쁜막내 서현", "010-0000-0014", "seohyun@sm.kr", R.drawable.img_like)
        )
        // 어댑터 생성 및 연결
        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
//                val name: String = (dataList[position] as MyItems.SmItem).aName
                val item = dataList[position]

                val name: String
                val image: Int
                val phonenumber: String
                val email: String
                when (item) {
                    is MyItems.SmItem -> {
                        name = item.aName
                        image = item.aIcon
                        phonenumber = item.aPhonenumber
                        email = item.aEmail
                    }
                    is MyItems.jypItem -> {
                        name = item.bName
                        image = item.bIcon
                        phonenumber = item.bPhonenumber
                        email = item.bEmail
                    }
                    else -> throw IllegalArgumentException("Unknown item type")
                }// 49 주석처리 & 50~69 추가
                Toast.makeText(requireContext(), " $name 선택!", Toast.LENGTH_SHORT).show()
                // 추가되는 부분
                // 데이터를 Bundle로 패킹
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putInt("image", image)
                bundle.putString("phonenumber", phonenumber)
                bundle.putString("email", email)
                // ... 다른 데이터도 Bundle에 추가

                // MyPageFragment 인스턴스 생성 및 Bundle 설정
                val detailFragment = DetailPageFragment()
                detailFragment.arguments = bundle

                // 프래그먼트 트랜잭션 시작
                parentFragmentManager.beginTransaction()
                    .replace(
                        android.R.id.content,
                        detailFragment
                    ) // fragment_container는 교체하려는 뷰의 ID입니다.
                    .addToBackStack(null) // 뒤로 가기 버튼 처리를 위해 스택에 추가
                    .commit()
            }
        }


        return binding.root
    }

}