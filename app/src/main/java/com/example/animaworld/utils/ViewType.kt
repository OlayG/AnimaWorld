package com.example.animaworld.utils

interface ViewType {
    fun getViewType(): TYPE
}

enum class TYPE {
    LOADER,
    ITEM
}