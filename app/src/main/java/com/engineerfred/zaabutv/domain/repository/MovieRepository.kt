package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getFeaturedMovies(): Flow<List<Movie>>
    fun getMoviesByCategory(category: Category): Flow<List<Movie>>
    fun getMoviesByCountry(country: Country): Flow<List<Movie>>
    fun getMoviesByVj(vjId: String): Flow<List<Movie>>
    fun getMovieById(id: String): Flow<Movie?>
    fun searchMovies(
        query: String,
        vjId: String? = null,
        category: Category? = null,
        country: Country? = null
    ): Flow<List<Movie>>
    fun getSimilarMovies(movieId: String): Flow<List<Movie>>
}
