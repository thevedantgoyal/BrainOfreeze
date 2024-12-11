package com.example.brainofreeze.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState


    fun onEvent(event: EventHomeScreen) {
        when (event) {

            is EventHomeScreen.SetNumberOfquizzes -> {
                _homeState.value = homeState.value.copy(numberOfquizzes = event.numberofQuizzes)
            }

            is EventHomeScreen.SetCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)
            }

            is EventHomeScreen.SetType -> {
                _homeState.value = homeState.value.copy(type = event.type)
            }

            is EventHomeScreen.SetDifficulty -> {
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }

            else -> {

            }
        }

    }
}