package com.engineerfred.zaabutv.domain.usecase

import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import com.engineerfred.zaabutv.domain.repository.VjRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(
        query: String,
        vjId: String? = null,
        category: Category? = null,
        country: Country? = null
    ): Flow<List<Movie>> {
        return movieRepository.searchMovies(query, vjId, category, country)
    }
}

class GetVjsUseCase @Inject constructor(
    private val vjRepository: VjRepository
) {
    operator fun invoke(): Flow<List<Vj>> {
        return vjRepository.getVjs()
    }
}
