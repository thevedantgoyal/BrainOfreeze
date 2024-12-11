package com.example.brainofreeze.domain.repository

import com.example.brainofreeze.data.remote.QuizAPI
import com.example.brainofreeze.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(amount : Int , category : Int , difficulty : String , type : String) : List<Quiz>
}