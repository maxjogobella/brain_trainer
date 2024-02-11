package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.R
import com.example.brain_kid.databinding.FragmentLevelBinding
import com.example.brain_kid.domain.model.Level

class FragmentLevel : Fragment() {

    private var _binding : FragmentLevelBinding? = null
    private val binding : FragmentLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentLevelBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {

            buttonTestLevel.setOnClickListener {
                launchOnFragmentGame(level = Level.TEST)
            }

            buttonEasyLevel.setOnClickListener {
                launchOnFragmentGame(level = Level.EASY)
            }

            buttonMediumLevel.setOnClickListener {
                launchOnFragmentGame(level = Level.MEDIUM)
            }

            buttonHardLevel.setOnClickListener {
                launchOnFragmentGame(level = Level.HARD)
            }
        }
    }


    private fun launchOnFragmentGame(level : Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentGame.newInstance(level))
            .addToBackStack(FragmentGame.NAME_BACKSTACK)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val NAME = "FragmentLevel"
        fun newInstance() : FragmentLevel {
            return FragmentLevel()
        }
    }
}