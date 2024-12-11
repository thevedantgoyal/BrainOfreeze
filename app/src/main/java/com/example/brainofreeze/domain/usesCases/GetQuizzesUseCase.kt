package com.example.brainofreeze.domain.usesCases

import com.example.brainofreeze.common.Resource
import com.example.brainofreeze.domain.model.Quiz
import com.example.brainofreeze.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.lang.Exception

class GetQuizzesUseCase (

    val quizRepository: QuizRepository
){

    operator fun invoke(
        amount : Int,
        category : Int,
        difficulty : String,
        type : String
    ) : Flow<Resource<List<Quiz>>> = flow {

        emit(Resource.Loading())

        try {

            emit(Resource.Success(data = quizRepository.getQuizzes(amount,category,difficulty,type)))
        }catch (e : Exception){
            emit(Resource.Error(message = e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)

}