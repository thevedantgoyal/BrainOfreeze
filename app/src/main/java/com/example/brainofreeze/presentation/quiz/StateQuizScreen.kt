package com.example.brainofreeze.presentation.quiz

import com.example.brainofreeze.domain.model.Quiz

data class StateQuizScreen(
    val isLoading: Boolean = false,
    val quizState: List<QuizState> = listOf(),
    val error: String = "",
    val score : Int = 0
)


data class QuizState(
    val quiz: Quiz? = null,
    val shuffledOptions: List<String> = emptyList(),
    val selectedOption: Int? = -1
)

/*
in this we find the clicked option and save index in selectedOption and then find particular string with that index save in shuffled
 option then the match that string with the correct string in the quiz if yes then count = +1
*/