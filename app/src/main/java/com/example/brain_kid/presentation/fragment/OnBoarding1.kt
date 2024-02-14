package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.brain_kid.R
import com.example.brain_kid.databinding.Onboarding1Binding

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
        findNavController().navigate(R.id.action_onBoarding1_to_onBoarding2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}