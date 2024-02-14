package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.brain_kid.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOnBoarding2.setOnClickListener{
            launchOnFragmentLevel()
        }
    }

    private fun launchOnFragmentLevel() {
        findNavController().navigate(R.id.action_onBoarding2_to_fragmentLevel)
        commitPreferences(FragmentStart.EXTRA_KEY_ONBOARDING, SHOULD_SHOW_ONBOARDING)
    }

    private fun commitPreferences(
        key : String,
        value : Boolean
    ) {
        requireActivity().getSharedPreferences(
            FragmentStart.SHARED_PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        ).edit().putBoolean(key, value).apply()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SHOULD_SHOW_ONBOARDING = false
    }
}