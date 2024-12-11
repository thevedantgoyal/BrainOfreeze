package com.example.brainofreeze.data.repository

import com.example.brainofreeze.data.remote.QuizAPI
import com.example.brainofreeze.domain.model.Quiz
import com.example.brainofreeze.domain.repository.QuizRepository

class QuizRepositoryImple (
    private val quizAPI: QuizAPI
) : QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizAPI.getQuizzes(amount , category , difficulty , type).results
    }
}