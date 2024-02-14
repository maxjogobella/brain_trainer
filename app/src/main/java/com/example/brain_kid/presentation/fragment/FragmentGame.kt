package com.example.brain_kid.presentation.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.brain_kid.data.GameRepositoryImpl
import com.example.brain_kid.databinding.FragmentGameBinding
import com.example.brain_kid.domain.model.GameResult
import com.example.brain_kid.presentation.viewmodel.FragmentGameViewModel
import com.example.brain_kid.presentation.viewmodel.ViewModelFactory

class FragmentGame : Fragment() {

    private var _binding : FragmentGameBinding? = null
    private val args by navArgs<FragmentGameArgs>()

    private val viewModelFactory  by lazy {
        ViewModelFactory(GameRepositoryImpl, requireActivity().application, args.level)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FragmentGameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.option1)
            add(binding.option2)
            add(binding.option3)
            add(binding.option4)
        }
    }

    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException("Fragment tGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenerToOptions()

    }

    private fun setClickListenerToOptions() {

        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {

            seconds.observe(viewLifecycleOwner) { seconds ->
                binding.chronometer.text = seconds
            }

            question.observe(viewLifecycleOwner) { question ->
                with(binding) {
                    tvSumNumber.text = question.sum.toString()
                    tvVisibleNumber.text = question.visibleNumber.toString()

                    for (i in 0 until tvOptions.size) {
                        tvOptions[i].text = question.options[i].toString()
                    }
                }
            }

            percentOfRightAnswers.observe(viewLifecycleOwner) { percent ->
                binding.progressBar.setProgress(percent, true)
            }

            enoughCount.observe(viewLifecycleOwner) { enoughCount ->
                binding.tvAnswerProgress.setTextColor(getColorByState(enoughCount))
            }

            enoughPercent.observe(viewLifecycleOwner) { enoughPercent ->
                val color = getColorByState(enoughPercent)
                binding.progressBar.progressTintList = ColorStateList.valueOf(color)
            }

            minPercent.observe(viewLifecycleOwner) {
                binding.progressBar.secondaryProgress = it
            }

            gameResult.observe(viewLifecycleOwner) {
                launchFinishGame(it)
            }

            progressAnswers.observe(viewLifecycleOwner) {
                binding.tvAnswerProgress.text = it
            }
        }

    }

    private fun launchFinishGame(gameResult: GameResult) {
        findNavController().navigate(
            FragmentGameDirections.actionFragmentGameToFragmentResult(gameResult)
        )
    }

    private fun getColorByState(goodState : Boolean) : Int {

        val colorResId = if (goodState) {
            android.R.color.holo_green_dark
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}