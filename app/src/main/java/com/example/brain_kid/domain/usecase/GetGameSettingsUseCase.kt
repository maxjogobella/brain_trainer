package com.example.brain_kid.domain.usecase

import com.example.brain_kid.domain.model.GameSetting
import com.example.brain_kid.domain.model.Level
import com.example.brain_kid.domain.repository.GameRepository

class GetGameSettingsUseCase(

    private val repository: GameRepository
) {
    operator fun invoke(level : Level) : GameSetting {
        return repository.getGameSettings(level)
    }
}