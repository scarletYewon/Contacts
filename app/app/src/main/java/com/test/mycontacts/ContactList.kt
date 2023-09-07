package com.test.mycontacts

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
                }
                Toast.makeText(requireContext(), " $name 선택!", Toast.LENGTH_SHORT).show()
                // 동규 추가(72~91) : 상세페이지로 데이터 전달 추가
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
                adapter.notifyDataSetChanged()
            }
        }
        return binding.root


    }
    @SuppressLint("NotifyDataSetChanged") // 동규 추가
    fun addContact(name:String, number: String, mail:String, notificationTime:Int)
    {
        val addContact = MyItems.SmItem(R.drawable.basic,name,number,mail,R.drawable.img_like,notificationTime)
        defaultDataList.add(addContact) // 동규 수정
        context?.let { setNotification(it, notificationTime) } // 동규 추가 : 알림 설정 부분
        adapter.notifyDataSetChanged() // 어댑터    새로고침

        Log.d("ContactList", "dataList: $defaultDataList") // 동규 수정
    }
    fun setNotification(context: Context, notificationTime: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notificationIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // 편의상 1분을 1000ms (1초)로 가정. 실제 앱에서는 1분 = 60 * 1000ms 입니다.
        val triggerTime = when (notificationTime) {
            5 -> 5 * 1000
            10 -> 10 * 1000
            30 -> 30 * 1000
            else -> 0
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + triggerTime, pendingIntent)
    }
}