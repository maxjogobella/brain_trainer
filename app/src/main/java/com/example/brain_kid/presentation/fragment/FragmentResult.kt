package com.example.brain_kid.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.brain_kid.databinding.FragmentResultBinding
import com.example.brain_kid.domain.model.GameResult

class FragmentResult : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private lateinit var gameResult: GameResult
    private val binding : FragmentResultBinding
        get() = _binding ?: throw RuntimeException("FragmentResultBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                restartGame()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.buttonRetry.setOnClickListener {
            restartGame()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(EXTRA_KEY_GAME_RESULT, GameResult::class.java)?.let {
                gameResult = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun restartGame() {
        requireActivity().supportFragmentManager.popBackStack(
            FragmentGame.NAME_BACKSTACK, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    companion object {

        private const val EXTRA_KEY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult) : FragmentResult {
            return FragmentResult().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_GAME_RESULT, gameResult)
                }
            }
        }

    }
}