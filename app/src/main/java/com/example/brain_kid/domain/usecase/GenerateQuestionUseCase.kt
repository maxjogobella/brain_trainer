package com.example.brain_kid.domain.usecase

import com.example.brain_kid.domain.model.Question
import com.example.brain_kid.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository : GameRepository
) {
    operator fun invoke(maxSumValue : Int) : Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        const val COUNT_OF_OPTIONS = 4
    }
}