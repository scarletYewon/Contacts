package com.test.mycontacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.mycontacts.databinding.FragmentDetailPageBinding


class DetailPageFragment : Fragment() {
    private lateinit var binding: FragmentDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        binding.callBtn.setOnClickListener {}
        binding.messageBtn.setOnClickListener {}
        // Inflate the layout for this fragment
        return binding.root
    }
}