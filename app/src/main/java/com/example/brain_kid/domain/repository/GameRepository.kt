package com.example.brain_kid.domain.repository

import com.example.brain_kid.domain.model.GameSetting
import com.example.brain_kid.domain.model.Level
import com.example.brain_kid.domain.model.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue : Int,
        countOfOptions : Int
    ) : Question
    fun getGameSettings(level : Level) : GameSetting

}