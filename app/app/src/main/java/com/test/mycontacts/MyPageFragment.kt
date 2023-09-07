package com.test.mycontacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mycontacts.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        binding.callBtn.setOnClickListener {}
        binding.messageBtn.setOnClickListener {}
        // Inflate the layout for this fragment
        return binding.root
    }
}