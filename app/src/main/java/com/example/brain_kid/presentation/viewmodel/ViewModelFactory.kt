package com.example.brain_kid.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brain_kid.domain.model.Level
import com.example.brain_kid.domain.repository.GameRepository
import com.example.brain_kid.presentation.fragment.FragmentGame

class ViewModelFactory(
    private val repository: GameRepository,
    private val application : Application,
    private val level : Level
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentGameViewModel::class.java)) {
            return FragmentGameViewModel(repository, application, level) as T
        } else {
            throw RuntimeException("Unknown ViewModel class $modelClass")
        }
    }
}