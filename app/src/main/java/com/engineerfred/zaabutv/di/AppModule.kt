package com.engineerfred.zaabutv.di

import com.engineerfred.zaabutv.data.repository.ActorRepositoryImpl
import com.engineerfred.zaabutv.data.repository.AuthRepositoryImpl
import com.engineerfred.zaabutv.data.repository.MovieRepositoryImpl
import com.engineerfred.zaabutv.data.repository.SubscriptionRepositoryImpl
import com.engineerfred.zaabutv.data.repository.VjRepositoryImpl
import com.engineerfred.zaabutv.data.repository.WatchlistRepositoryImpl
import com.engineerfred.zaabutv.domain.repository.ActorRepository
import com.engineerfred.zaabutv.domain.repository.AuthRepository
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import com.engineerfred.zaabutv.domain.repository.SubscriptionRepository
import com.engineerfred.zaabutv.domain.repository.VjRepository
import com.engineerfred.zaabutv.domain.repository.WatchlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindVjRepository(
        impl: VjRepositoryImpl
    ): VjRepository

    @Binds
    @Singleton
    abstract fun bindActorRepository(
        impl: ActorRepositoryImpl
    ): ActorRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSubscriptionRepository(
        impl: SubscriptionRepositoryImpl
    ): SubscriptionRepository

    @Binds
    @Singleton
    abstract fun bindWatchlistRepository(
        impl: WatchlistRepositoryImpl
    ): WatchlistRepository
}
