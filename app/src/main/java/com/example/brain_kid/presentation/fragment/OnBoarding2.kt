package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.databinding.Onboarding2Binding

class OnBoarding2 : Fragment() {

    private var _binding : Onboarding2Binding? = null
    private val binding : Onboarding2Binding
        get() = _binding ?: throw RuntimeException("OnBoarding2 == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Onboarding2Binding.inflate(inflater, container, false)
        return binding.root
    }
}