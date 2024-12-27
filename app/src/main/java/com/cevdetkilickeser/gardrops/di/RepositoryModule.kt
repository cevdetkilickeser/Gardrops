package com.cevdetkilickeser.gardrops.di

import com.cevdetkilickeser.gardrops.data.repository.AuthRepository
import com.cevdetkilickeser.gardrops.domain.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

}