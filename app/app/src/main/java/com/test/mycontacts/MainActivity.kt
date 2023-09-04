package com.test.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.test.mycontacts.databinding.ActivityMainBinding
import com.test.mycontacts.databinding.FragmentContactDetailBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            ContactdetailBtn.setOnClickListener{
                setFragment(ContactDetailFragment())
            }
            MypageBtn.setOnClickListener {
                setFragment(MyPageFragment())
            }
            messageBtn.setOnClickListener{

            }
            callBtn.setOnClickListener {

            }
        }
        setFragment(ContactDetailFragment())
    }
    private fun setFragment(frag : Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
}


// 1.