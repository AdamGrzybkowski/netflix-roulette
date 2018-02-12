package com.adamg.gimmemovie.view.roulette

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adamg.gimmemovie.repository.ShowRepository

@Suppress("UNCHECKED_CAST")
class RouletteViewModelFactory(private val showRepository: ShowRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouletteViewModel::class.java)) {
            return RouletteViewModel(showRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}