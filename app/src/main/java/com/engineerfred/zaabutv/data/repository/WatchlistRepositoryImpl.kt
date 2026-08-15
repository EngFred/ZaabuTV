package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockMovies
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistRepositoryImpl @Inject constructor() : WatchlistRepository {

    // Initial watchlist items for demo realism
    private val watchlistIds = MutableStateFlow(
        mutableSetOf("movie_1", "movie_4", "movie_15", "movie_19")
    )

    override fun getWatchlist(): Flow<List<Movie>> {
        return watchlistIds.map { ids ->
            MockMovies.movies.filter { ids.contains(it.id) }
        }
    }

    override fun isInWatchlist(movieId: String): Flow<Boolean> {
        return watchlistIds.map { ids -> ids.contains(movieId) }
    }

    override fun toggleWatchlist(movieId: String): Flow<Boolean> {
        val current = watchlistIds.value.toMutableSet()
        val inList = if (current.contains(movieId)) {
            current.remove(movieId)
            false
        } else {
            current.add(movieId)
            true
        }
        watchlistIds.value = current
        return MutableStateFlow(inList)
    }
}
