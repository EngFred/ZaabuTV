package com.engineerfred.zaabutv.domain.usecase

import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.repository.ActorRepository
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import com.engineerfred.zaabutv.domain.repository.VjRepository
import com.engineerfred.zaabutv.domain.repository.WatchlistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

data class MovieDetailData(
    val movie: Movie,
    val vj: Vj?,
    val cast: List<Actor>,
    val similarMovies: List<Movie>,
    val isInWatchlist: Boolean
)

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val vjRepository: VjRepository,
    private val actorRepository: ActorRepository,
    private val watchlistRepository: WatchlistRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(movieId: String): Flow<MovieDetailData?> {
        return movieRepository.getMovieById(movieId).flatMapLatest { movie ->
            if (movie == null) return@flatMapLatest flowOf(null)

            val vjFlow = movie.vjId?.let { vjRepository.getVjById(it) } ?: flowOf(null)
            val castFlow = if (movie.castIds.isNotEmpty()) {
                actorRepository.getActorsByIds(movie.castIds)
            } else flowOf(emptyList())
            val similarFlow = movieRepository.getSimilarMovies(movieId)
            val watchlistFlow = watchlistRepository.isInWatchlist(movieId)

            combine(vjFlow, castFlow, similarFlow, watchlistFlow) { vj, cast, similar, inWatchlist ->
                MovieDetailData(
                    movie = movie,
                    vj = vj,
                    cast = cast,
                    similarMovies = similar,
                    isInWatchlist = inWatchlist
                )
            }
        }
    }
}
