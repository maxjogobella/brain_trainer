package com.example.brain_kid.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brain_kid.R
import com.example.brain_kid.data.GameRepositoryImpl
import com.example.brain_kid.databinding.FragmentGameBinding
import com.example.brain_kid.domain.model.GameResult
import com.example.brain_kid.domain.model.GameSetting
import com.example.brain_kid.domain.model.Level
import com.example.brain_kid.presentation.viewmodel.FragmentGameViewModel

class FragmentGame : Fragment() {


    private val viewModel : FragmentGameViewModel by lazy {
        FragmentGameViewModel(
            repository = GameRepositoryImpl,
            application = requireActivity().application)
    }

    private var _binding : FragmentGameBinding? = null
    private lateinit var level : Level
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvQuestionNumber.setOnClickListener {

            val gameSetting = GameSetting(
                maxSumValue = 20,
                minCountOfRightAnswers = 20,
                minPercentOfRightAnswers = 15,
                gameTimeInSeconds = 20,
            )
            val gameResult = GameResult(
                winner = true,
                countOfRightAnswers = 20,
                countOfQuestions = 10,
                gameSettings = gameSetting
            )
            val fragment = FragmentResult.newInstance(
                gameResult = gameResult
            )

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit()
        }

////        viewModel.setGameSettings(level)
//
//        viewModel.numberSum.observe(viewLifecycleOwner) {
//            binding.tvSumNumber.text = it.toString()
//        }
//
//        viewModel.visibleNumber.observe(viewLifecycleOwner) {
//            binding.tvVisibleNumber.text = it.toString()
//        }

        viewModel.seconds.observe(viewLifecycleOwner) {
            binding.chronometer.text = it.toString()
        }

        viewModel.listOfOptions.observe(viewLifecycleOwner) {list ->
            binding.option1.text = list[0].toString()
            binding.option2.text = list[1].toString()
            binding.option3.text = list[2].toString()
            binding.option4.text = list[3].toString()
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(EXTRA_KEY_LEVEL, Level::class.java)?.let {
                level = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_KEY_LEVEL = "level"
        const val NAME_BACKSTACK = "FragmentGame"
        fun newInstance(level : Level) : FragmentGame {
            return FragmentGame().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_KEY_LEVEL, level)
                }
            }
        }
    }
}