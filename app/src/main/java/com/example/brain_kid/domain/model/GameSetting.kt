package com.example.brain_kid.domain.model

data class GameSetting(
    val maxSumValue : Int,
    val minCountOfRightAnswers : Int,
    val minPercentOfRightAnswers : Int,
    val gameTimeInSeconds : Int
)