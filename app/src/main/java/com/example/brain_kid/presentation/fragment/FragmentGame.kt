package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.databinding.FragmentGameBinding
import java.lang.RuntimeException
import java.util.logging.Level

class FragmentGame : Fragment() {

    private var _binding : FragmentGameBinding? = null
    private lateinit var level : com.example.brain_kid.domain.model.Level
    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        level = requireArguments().getSerializable(EXTRA_KEY_LEVEL) as com.example.brain_kid.domain.model.Level
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val EXTRA_KEY_LEVEL = "level"
        fun newInstance(level : com.example.brain_kid.domain.model.Level) : FragmentGame {
            return FragmentGame().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_KEY_LEVEL, level)
                }
            }
        }
    }
}