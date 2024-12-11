package com.example.brainofreeze.data.remote

import com.example.brainofreeze.data.remote.dto.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizAPI {

    @GET("api.php")
    suspend fun getQuizzes(
        @Query("amount") amount : Int,
        @Query("category") category : Int,
        @Query("difficulty") difficulty : String,
        @Query("type") type : String
        ) : QuizResponse
    }
