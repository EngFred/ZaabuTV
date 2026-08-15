package com.engineerfred.zaabutv.domain.model

data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val synopsis: String,
    val genres: List<String>,
    val releaseYear: Int,
    val durationMinutes: Int,
    val country: Country,
    val categories: List<Category>,
    val vjId: String? = null, // null for native Luganda Ugandan movies
    val vjName: String? = null, // resolved VJ name for display
    val castIds: List<String> = emptyList(),
    val rating: Float = 4.5f,
    val isFeatured: Boolean = false
) {
    val durationFormatted: String
        get() {
            val hours = durationMinutes / 60
            val mins = durationMinutes % 60
            return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
        }
}
