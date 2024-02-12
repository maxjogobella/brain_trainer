package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.R
import com.example.brain_kid.databinding.FragmentResultBinding
import com.example.brain_kid.databinding.FragmentRulesBinding
import com.example.brain_kid.presentation.FragmentUtils
import com.example.brain_kid.presentation.MainActivity

class FragmentRules : Fragment() {

    private var _binding : FragmentRulesBinding? = null
    private val binding : FragmentRulesBinding
        get() = _binding ?: throw RuntimeException("FragmentRulesBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFromRule.setOnClickListener {
            launchOnFragmentLevel()
        }
    }

    private fun launchOnFragmentLevel() {
        FragmentUtils.launchFragmentWithAnimation(
            requireActivity().supportFragmentManager,
            R.id.main_container,
            FragmentLevel.newInstance()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : FragmentRules {
            return FragmentRules()
        }
    }
}