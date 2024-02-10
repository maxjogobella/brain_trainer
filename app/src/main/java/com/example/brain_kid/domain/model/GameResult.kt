package com.example.brain_kid.domain.model

data class GameResult(
    val winner : Boolean,
    val countOfRightAnswers : Int,
    val countOfQuestions : Int,
    val gameSettings : GameSetting
)