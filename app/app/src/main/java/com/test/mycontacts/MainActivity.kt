package com.test.mycontacts


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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


            dialog.setOnButtonClickListener(object : AddDialog.ButtonClickListener {
                override fun onClicked(name: String,number: String,mail:String) {
                    val contactListFragment = pagerAdapter.getItem(0) as ContactList
                    contactListFragment.addContact(name,number,mail)
                    ContactList()
                    Toast.makeText(this@MainActivity,"${name} 전달 확인",Toast.LENGTH_LONG).show()
                    }
                }
            )

            dialog.dig()
            dialog.show()
        }
    }
}