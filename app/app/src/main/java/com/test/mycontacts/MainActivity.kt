package com.test.mycontacts


import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            dialogBinding = DialogBinding.inflate(layoutInflater)
            addDialog = AddDialog(this, dialogBinding)

            addDialog.onDataAdded = { // 위에서 초기화한 AddDialog에 onDataAdded?.let { it() }에 실행문이 전달되고 실행된다.
                pager.adapter = pagerAdapter // ViewPager 어댑터 설정
                pager.setCurrentItem(0, true)
            }

            addDialog.setOnButtonClickListener(object : AddDialog.ButtonClickListener {
                override fun onClicked(uri: Uri?, name: String, number: String, mail: String, notificationTime: Int) {

//                    Log.d("imageUri3","imageUri:${uri}") // 잘 추가됫는지 Log 확인용
                    val contactListFragment = pagerAdapter.getItem(0) as ContactList
                    contactListFragment.addContact(uri,name, number, mail, notificationTime)
//                    Log.d("DataListCheck", "Size of dataList: ${defaultDataList.size}") // 데이터 추가됫는지 확인용 Logcat
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
//            Log.d("ImageUri4", "$imageUri") 잘 추가됫는지 Log 확인용
            addDialog.setImageUri(imageUri)
        }
    }
}
