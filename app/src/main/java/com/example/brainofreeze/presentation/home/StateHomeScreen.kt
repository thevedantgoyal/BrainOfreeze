package com.example.brainofreeze.presentation.home

data class StateHomeScreen(
    val numberOfquizzes: Int = 10,
    val category: String = "General Knowledge",
    val difficulty: String = "Easy",
    val type: String = "Multiple Choice"
)