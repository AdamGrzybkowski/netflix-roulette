package com.adamg.gimmemovie.view.roulette

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adamg.gimmemovie.repository.RouletteRepository

@Suppress("UNCHECKED_CAST")
class RouletteViewModelFactory(private val rouletteRepository: RouletteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouletteViewModel::class.java)) {
            return RouletteViewModel(rouletteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}