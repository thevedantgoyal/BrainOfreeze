package com.example.brainofreeze.presentation.quiz

sealed class EventQuizScreen {

    data class getQuiz(val numberOfQuiz : Int , val category: Int , val difficulty : String , val type : String) : EventQuizScreen()

    data class setOptionSelected(val quizStateIndex  : Int , val selectedOption : Int) : EventQuizScreen()
}