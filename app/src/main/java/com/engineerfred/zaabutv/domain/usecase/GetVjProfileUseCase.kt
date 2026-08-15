package com.engineerfred.zaabutv.domain.usecase

import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import com.engineerfred.zaabutv.domain.repository.VjRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

data class VjProfileData(
    val vj: Vj,
    val translatedMovies: List<Movie>
)

class GetVjProfileUseCase @Inject constructor(
    private val vjRepository: VjRepository,
    private val movieRepository: MovieRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(vjId: String): Flow<VjProfileData?> {
        return vjRepository.getVjById(vjId).flatMapLatest { vj ->
            if (vj == null) return@flatMapLatest flowOf(null)
            movieRepository.getMoviesByVj(vjId).combine(flowOf(vj)) { movies, vjItem ->
                VjProfileData(vj = vjItem, translatedMovies = movies)
            }
        }
    }
}
