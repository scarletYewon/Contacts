package com.test.mycontacts

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mycontacts.databinding.FragmentDetailPageBinding


class DetailPageFragment : Fragment() {
    private lateinit var binding: FragmentDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        binding.callBtn.setOnClickListener {}
        binding.messageBtn.setOnClickListener {}

        val name = arguments?.getString("name")
        val phonenumber = arguments?.getString("phonenumber")
        val email = arguments?.getString("email")
        val notifi = arguments?.getInt("notifi")
        val imageUri: Uri? = arguments?.getParcelable("image")
        binding.imagename.text = name
        binding.phonenumberTv2.text = phonenumber
        binding.email2Tv.text = email
        binding.notificationTv2.text = notifi.toString() + "분 후 알림"
//        arguments?.let {
//            binding.image.setImageResource(it.getInt("image"))
//        } ?: run {} // Uri 이미지 datalist에 추가하기 구현 성공
        imageUri?.let {
            binding.image.setImageURI(it)
        }
        return binding.root
    }
}