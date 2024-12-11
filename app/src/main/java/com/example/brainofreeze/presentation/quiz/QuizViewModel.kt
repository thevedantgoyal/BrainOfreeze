package com.example.brainofreeze.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainofreeze.common.Resource
import com.example.brainofreeze.domain.model.Quiz
import com.example.brainofreeze.domain.usesCases.GetQuizzesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCase: GetQuizzesUseCase) :
    ViewModel() {

    val _quizList = MutableStateFlow(StateQuizScreen())
    private val quizList = _quizList


    fun onEvent(event: EventQuizScreen) {
        when (event) {
            is EventQuizScreen.getQuiz -> {
                getQuizzes(event.numberOfQuiz, event.category, event.difficulty, event.type)
            }
            is EventQuizScreen.setOptionSelected ->{
                updateQuizteStaList(event.quizStateIndex , event.selectedOption)
            }

            else -> {}
        }
    }

    private fun updateQuizteStaList(quizStateIndex: Int, selectedOption: Int) {
         val updateQuizList = mutableListOf<QuizState>()
        quizList.value.quizState.forEachIndexed{index , quizState ->
            updateQuizList.add(
                if(quizStateIndex == index){
                    quizState.copy(selectedOption = selectedOption)
                }
                else quizState
            )
        }
        _quizList.value = quizList.value.copy(quizState = updateQuizList)

        updateScore(_quizList.value.quizState[quizStateIndex])

    }

    private fun updateScore(quizState: QuizState) {
        if(quizState.selectedOption != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOption?.let {
                quizState.shuffledOptions[it].replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'")
            }
            Log.d("check" , "$correctAnswer -> $selectedAnswer")
            if(correctAnswer == selectedAnswer){
                val previousScore  = _quizList.value.score
                _quizList.value = quizList.value.copy(score = previousScore + 1)
            }
        }
    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            getQuizzesUseCase(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }

                    is Resource.Success -> {
                        val listOfQuizState: List<QuizState> = getlistofquizzzes(resource.data)
                        _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                    }

                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }

                    else -> {}

                }
            }
        }

    }

    private fun getlistofquizzzes(data: List<Quiz>?): List<QuizState> {
        val listOfQuizState = mutableListOf<QuizState>()

        for (quiz in data!!) {
            val shuffledOption = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }
            listOfQuizState.add(QuizState(quiz, shuffledOption, -1))
        }
        return listOfQuizState
    }
}