package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.brain_kid.R
import com.example.brain_kid.databinding.FragmentStartBinding

class FragmentStart : Fragment() {

    private var _binding : FragmentStartBinding? = null
    private val binding : FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStart == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val valueSharedPref = getPreferences(EXTRA_KEY_ONBOARDING, SHOULD_SHOW_ONBOARDING)

        if (valueSharedPref) {
            findNavController().navigate(R.id.action_fragmentStart_to_onBoarding1)
        } else {
            findNavController().navigate(R.id.action_fragmentStart_to_fragmentLevel)
        }
    }


    private fun getPreferences(
        key : String,
        defaultValue : Boolean
    ) : Boolean {
        return requireActivity().getSharedPreferences(SHARED_PREF_NAME, AppCompatActivity.MODE_PRIVATE
        ).getBoolean(EXTRA_KEY_ONBOARDING, SHOULD_SHOW_ONBOARDING)
    }

    companion object {
        const val EXTRA_KEY_ONBOARDING = "on_boarding"
        private const val SHOULD_SHOW_ONBOARDING = true
        const val SHARED_PREF_NAME = "pref_name_fake"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}