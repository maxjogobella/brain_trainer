package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.brain_kid.R
import com.example.brain_kid.databinding.Onboarding2Binding
import com.example.brain_kid.presentation.FragmentUtils
import com.example.brain_kid.presentation.MainActivity

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonOnBoarding2.setOnClickListener{
            launchOnFragmentLevel()
        }
    }

    private fun launchOnFragmentLevel() {
        FragmentUtils.launchFragmentWithAnimation(
            requireActivity().supportFragmentManager,
            R.id.main_container,
            FragmentLevel.newInstance()
        )

        commitPreferences(MainActivity.SHARED_PREF_KEY_ONBOARDING,
            MainActivity.ONBOARDING_INVISIBLE)
    }

    private fun commitPreferences(
        key : String,
        value : Int
    ) {
        requireActivity().getSharedPreferences(
            MainActivity.SHARED_PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        ).edit().putInt(key, value).apply()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : OnBoarding2 {
            return OnBoarding2()
        }
    }
}