package com.example.brain_kid.presentation.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brain_kid.R
import com.example.brain_kid.domain.model.GameResult
import com.example.brain_kid.domain.model.GameSetting
import com.example.brain_kid.domain.model.Level
import com.example.brain_kid.domain.model.Question
import com.example.brain_kid.domain.repository.GameRepository
import com.example.brain_kid.domain.usecase.GenerateQuestionUseCase
import com.example.brain_kid.domain.usecase.GetGameSettingsUseCase

class FragmentGameViewModel(
    application : Application,
    repository: GameRepository
) : AndroidViewModel(application) {

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private var timer : CountDownTimer? = null
    private lateinit var gameSetting: GameSetting
    private lateinit var level : Level
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0
    private val context = application

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers : LiveData<Int>
        get() = _percentOfRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers : LiveData<String>
        get() = _progressAnswers

    private val _question = MutableLiveData<Question>()
    val question : LiveData<Question>
        get() = _question

    private val _numberSum = MutableLiveData<Int>()
    val numberSum : LiveData<Int>
        get() = _numberSum

    private val _seconds = MutableLiveData<String>()
    val seconds : LiveData<String>
        get() = _seconds

    private val _listOfOptions = MutableLiveData<List<Int>>()
    val listOfOptions : LiveData<List<Int>>
        get() = _listOfOptions

    private val _enoughCount = MutableLiveData<Boolean>()
    val enoughCount : LiveData<Boolean>
        get() = _enoughCount

    private val _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent : LiveData<Boolean>
        get() = _enoughPercent

    private val _minPercent = MutableLiveData<Int>()
    val minPercent : LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult : LiveData<GameResult>
        get() = _gameResult



    fun startGame(level : Level) {
        getGameSettings(level)
        startTimer()
        generateQuestion()
    }

    private fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun checkAnswer(number : Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    private fun getGameSettings(level : Level) {
        this.level = level
        this.gameSetting = getGameSettingsUseCase(level)
        _minPercent.value = gameSetting.minPercentOfRightAnswers
    }
    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSetting.maxSumValue)
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswers.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.percent_right),
            countOfRightAnswers,
            gameSetting.minCountOfRightAnswers
        )
        _enoughCount.value = countOfRightAnswers >= gameSetting.minCountOfRightAnswers
        _enoughPercent.value = percent >= gameSetting.minPercentOfRightAnswers
    }

    private fun calculatePercentOfRightAnswers() : Int {
        return ((countOfRightAnswers.toDouble() / countOfQuestions) * 100).toInt()
    }

    private fun startTimer() {

       timer =  object : CountDownTimer(
           gameSetting.gameTimeInSeconds * MILLIS_IN_SECONDS,
           MILLIS_IN_SECONDS
        ) {

            override fun onTick(millisUntilFinished: Long) {
                _seconds.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }

        }

        timer?.start()
    }

    private fun finishGame() {
        val gameResult = GameResult(
            winner = enoughCount.value == true && enoughPercent.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = countOfQuestions,
            gameSettings = gameSetting
        )
        _gameResult.value = gameResult
    }

    private fun formatTime(millisUntilFinished : Long) : String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)

        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {

        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60

    }
}