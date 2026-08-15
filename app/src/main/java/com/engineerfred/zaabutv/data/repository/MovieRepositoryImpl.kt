package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockMovies
import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor() : MovieRepository {

    override fun getFeaturedMovies(): Flow<List<Movie>> = flow {
        delay(200)
        emit(MockMovies.getFeatured())
    }

    override fun getMoviesByCategory(category: Category): Flow<List<Movie>> = flow {
        delay(200)
        emit(MockMovies.getByCategory(category))
    }

    override fun getMoviesByCountry(country: Country): Flow<List<Movie>> = flow {
        delay(200)
        emit(MockMovies.getByCountry(country))
    }

    override fun getMoviesByVj(vjId: String): Flow<List<Movie>> = flow {
        delay(200)
        emit(MockMovies.getByVj(vjId))
    }

    override fun getMovieById(id: String): Flow<Movie?> = flow {
        delay(200)
        emit(MockMovies.getById(id))
    }

    override fun searchMovies(
        query: String,
        vjId: String?,
        category: Category?,
        country: Country?
    ): Flow<List<Movie>> = flow {
        delay(150)
        emit(MockMovies.search(query, vjId, category, country))
    }

    override fun getSimilarMovies(movieId: String): Flow<List<Movie>> = flow {
        delay(200)
        val currentMovie = MockMovies.getById(movieId)
        val similar = if (currentMovie != null) {
            MockMovies.movies.filter {
                it.id != movieId && (it.country == currentMovie.country || it.vjId == currentMovie.vjId)
            }.take(6)
        } else {
            MockMovies.movies.take(6)
        }
        emit(similar)
    }
}
