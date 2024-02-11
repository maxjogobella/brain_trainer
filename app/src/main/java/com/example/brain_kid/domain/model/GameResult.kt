package com.example.brain_kid.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner : Boolean,
    val countOfRightAnswers : Int,
    val countOfQuestions : Int,
    val gameSettings : GameSetting
) : Parcelable