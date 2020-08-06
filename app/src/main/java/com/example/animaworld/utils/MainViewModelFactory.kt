package com.example.animaworld.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animaworld.data.repository.KitsuRepo
import com.example.animaworld.ui.main.viewmodel.MainViewModel

class MainViewModelFactory(private val kitsuRepo: KitsuRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel(kitsuRepo) as T
}