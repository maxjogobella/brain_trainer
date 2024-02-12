package com.example.brain_kid.presentation.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.R
import com.example.brain_kid.databinding.Onboarding1Binding
import com.example.brain_kid.presentation.FragmentUtils

class OnBoarding1 : Fragment() {

    private var _binding: Onboarding1Binding? = null
    private val binding: Onboarding1Binding
        get() = _binding ?: throw RuntimeException("OnBoarding1 == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Onboarding1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {
            launchOnBoarding2()
        }
    }

    private fun launchOnBoarding2() {
        FragmentUtils.launchFragmentWithAnimation(
            requireActivity().supportFragmentManager,
            R.id.main_container,
            OnBoarding2.newInstance()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : OnBoarding1 {
            return OnBoarding1()
        }
    }

}