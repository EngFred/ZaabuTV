package com.engineerfred.zaabutv.domain.usecase

import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie
import com.engineerfred.zaabutv.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class HomeContent(
    val featured: List<Movie>,
    val newUploads: List<Movie>,
    val classics: List<Movie>,
    val ugandanFilms: List<Movie>,
    val nollywoodFilms: List<Movie>,
    val ghanianFilms: List<Movie>
)

class GetHomeContentUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    @Suppress("UNCHECKED_CAST")
    operator fun invoke(): Flow<HomeContent> {
        return combine(
            movieRepository.getFeaturedMovies(),
            movieRepository.getMoviesByCategory(Category.NEW_UPLOADS),
            movieRepository.getMoviesByCategory(Category.CLASSICS),
            movieRepository.getMoviesByCountry(Country.UGANDA),
            movieRepository.getMoviesByCountry(Country.NIGERIA),
            movieRepository.getMoviesByCountry(Country.GHANA)
        ) { array ->
            HomeContent(
                featured = array[0] as List<Movie>,
                newUploads = array[1] as List<Movie>,
                classics = array[2] as List<Movie>,
                ugandanFilms = array[3] as List<Movie>,
                nollywoodFilms = array[4] as List<Movie>,
                ghanianFilms = array[5] as List<Movie>
            )
        }
    }
}
