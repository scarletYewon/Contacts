package com.test.mycontacts


import android.app.Activity
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
    private lateinit var addDialog: AddDialog

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
            // 다이어로그와 바인딩을 한 번만 초기화합니다.
            dialogBinding = DialogBinding.inflate(layoutInflater)
            addDialog = AddDialog(this, dialogBinding)

            addDialog.onDataAdded = {
                pager.adapter = pagerAdapter
                pager.setCurrentItem(0, true)
            }

            addDialog.setOnButtonClickListener(object : AddDialog.ButtonClickListener {
                override fun onClicked(name: String, number: String, mail: String, notificationTime: Int) {
                    val contactListFragment = pagerAdapter.getItem(0) as ContactList
                    contactListFragment.addContact(name, number, mail, notificationTime)
                    Log.d("DataListCheck", "Size of dataList: ${defaultDataList.size}")
                    Toast.makeText(this@MainActivity, "${name} 전달 확인", Toast.LENGTH_LONG).show()
                }
            })

            addDialog.dig()
            addDialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddDialog.REQUEST_GALLERY_DIALOG && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            Log.d("ImageUri", "$imageUri")
            addDialog.setImageUri(imageUri)
        }
    }
}
