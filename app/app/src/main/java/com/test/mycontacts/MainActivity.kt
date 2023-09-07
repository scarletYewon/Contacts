package com.test.mycontacts


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.test.mycontacts.MyItems.Companion.defaultDataList
import com.test.mycontacts.databinding.ActivityMainBinding
import com.test.mycontacts.databinding.DialogBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val pager = binding.viewPager
        pager.adapter = pagerAdapter
        val tab = binding.tab
        tab.setupWithViewPager(pager)

        binding.fbAdd.setOnClickListener{
            dialogBinding = DialogBinding.inflate(layoutInflater)
            val dialog = AddDialog(this, dialogBinding)
            // 여기에 onDataAdded 콜백을 설정합니다.
            dialog.onDataAdded = {
                pager.adapter = pagerAdapter
                pager.setCurrentItem(0, true)
            }

            dialog.setOnButtonClickListener(object : AddDialog.ButtonClickListener {
                override fun onClicked(name: String,number: String,mail:String,notificationTime:Int) { // 동규 수정
                    val contactListFragment = pagerAdapter.getItem(0) as ContactList
                    contactListFragment.addContact(name,number,mail,notificationTime) // 동규 수정
                    Log.d("DataListCheck", "Size of dataList: ${defaultDataList.size}")
//                    ContactList() // 동규 주석
                    Toast.makeText(this@MainActivity,"${name} 전달 확인",Toast.LENGTH_LONG).show()

                }
                }
            )

            dialog.dig()
            dialog.show()
        }
    }
}