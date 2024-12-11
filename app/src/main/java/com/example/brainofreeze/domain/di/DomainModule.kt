package com.example.brainofreeze.domain.di

import com.example.brainofreeze.data.remote.QuizAPI
import com.example.brainofreeze.data.repository.QuizRepositoryImple
import com.example.brainofreeze.domain.repository.QuizRepository
import com.example.brainofreeze.domain.usesCases.GetQuizzesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository) : GetQuizzesUseCase{
        return GetQuizzesUseCase(quizRepository)
    }

}