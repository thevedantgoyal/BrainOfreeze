package com.example.brainofreeze.presentation.home

sealed class EventHomeScreen {

    data class SetNumberOfquizzes (val numberofQuizzes : Int) : EventHomeScreen()
    data class SetCategory (val category : String) : EventHomeScreen()
    data class SetDifficulty (val difficulty : String) : EventHomeScreen()
    data class SetType (val type : String) : EventHomeScreen()

}