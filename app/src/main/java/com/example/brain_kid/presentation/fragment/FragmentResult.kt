package com.example.brain_kid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.brain_kid.R
import com.example.brain_kid.databinding.FragmentResultBinding

class FragmentResult : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val args by navArgs<FragmentResultArgs>()

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
        bindViews()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isAdded) {
                    restartGame()
                }
            }
        }
      requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        binding.buttonRetry.setOnClickListener {
            restartGame()
        }

    }

    private fun bindViews() {

        binding.gameResult = args.gameResult

        with(binding) {
            emojiResult.setImageResource(getSmilesResId())
//            tvRequiredAnswers.text = String.format(
//                getString(R.string.required_score),
//                args.gameResult.gameSettings.minCountOfRightAnswers,
//            )
//
//            tvScoreAnswers.text = String.format(
//                getString(R.string.score_answers),
//                args.gameResult.countOfRightAnswers
//            )
//
//            tvRequiredPercentage.text = String.format(
//                getString(R.string.required_percentage),
//                args.gameResult.gameSettings.minPercentOfRightAnswers
//            )

            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )

        }

    }

    private fun getSmilesResId() : Int {
        return if (args.gameResult.winner) {
            R.drawable.win
        } else {
            R.drawable.lose
        }
    }

    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun restartGame() {
        findNavController().navigate(R.id.action_fragmentResult_to_fragmentLevel)

    }
}