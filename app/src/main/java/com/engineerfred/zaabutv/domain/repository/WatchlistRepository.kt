package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {
    fun getWatchlist(): Flow<List<Movie>>
    fun isInWatchlist(movieId: String): Flow<Boolean>
    fun toggleWatchlist(movieId: String): Flow<Boolean>
}
