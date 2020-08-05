package com.example.animaworld.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.animaworld.R
import com.example.animaworld.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.fetchAnime()
    }
}