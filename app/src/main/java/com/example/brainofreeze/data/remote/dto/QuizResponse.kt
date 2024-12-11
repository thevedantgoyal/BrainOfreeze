package com.example.brainofreeze.data.remote.dto

import com.example.brainofreeze.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)