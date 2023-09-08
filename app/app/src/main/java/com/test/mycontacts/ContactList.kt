package com.test.mycontacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ex6_simplelistview.MyAdapter
import com.test.mycontacts.MyItems.Companion.defaultDataList
import com.test.mycontacts.databinding.FragmentDuduBinding

class ContactList : Fragment() {

    private lateinit var binding: FragmentDuduBinding
//    private val dataList = mutableListOf<MyItems>()
    private val adapter = MyAdapter(defaultDataList)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // 동규 수정 : MyItems의 데이터 참조
//        dataList.addAll(MyItems.defaultDataList)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDuduBinding.inflate(inflater, container, false)

        // 데이터 원본 준비 // 동규 수정(24~39) : 전화번호,이메일 추가

        // 어댑터 생성 및 연결
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

//val name: String = (dataList[position] as MyItems.SmItem).aName 동규 수정(48) : 주석 처리
                val item = defaultDataList[position] // 동규 추가(49~69) : 상세페이지로 데이터 전달 추가

                val name: String
                val image: Int
                val phonenumber: String
                val email: String
                val notifi: Int
                when (item) {
                    is MyItems.Item -> {
                        name = item.aName
                        image = item.aIcon
                        phonenumber = item.aPhonenumber
                        email = item.aEmail
                        notifi = item.notifi
                    }
                }
                Toast.makeText(requireContext(), " $name 선택!", Toast.LENGTH_SHORT).show()
                // 동규 추가(72~91) : 상세페이지로 데이터 전달 추가
                // 데이터를 Bundle로 패킹
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putInt("image", image)
                bundle.putString("phonenumber", phonenumber)
                bundle.putString("email", email)
                bundle.putInt("notifi", notifi)
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
                adapter.notifyDataSetChanged()
            }
        }
        return binding.root


    }
    @SuppressLint("NotifyDataSetChanged") // 동규 추가
    fun addContact(name:String, number: String, mail:String, notificationTime:Int)
    {
        val addContact = MyItems.Item(R.drawable.basic,name,number,mail,R.drawable.img_like3,notificationTime,0)
        defaultDataList.add(addContact) // 동규 수정
        adapter.notifyDataSetChanged() // 어댑터 새로고침

        Log.d("ContactList", "dataList: $defaultDataList") // 동규 수정
    }
}
