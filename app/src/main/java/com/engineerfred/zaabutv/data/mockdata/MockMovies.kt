package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie

object MockMovies {
    val movies = listOf(
        // ─── Featured / Hero Carousel ─────────────────────────────────────
        Movie(
            id = "movie_1",
            title = "The Return of Chief Ebuka",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A powerful Lagos billionaire returns home after 10 years to find his family empire targeted by a ruthless criminal syndicate. Translated into explosive Luganda by VJ Junior.",
            genres = listOf("Action", "Nollywood", "Crime Drama"),
            releaseYear = 2024,
            durationMinutes = 118,
            country = Country.NIGERIA,
            categories = listOf(Category.LATEST, Category.NIGERIA),
            vjId = "vj_junior",
            castIds = listOf("actor_ramsey", "actor_zubby", "actor_funke"),
            rating = 4.9f,
            isFeatured = true
        ),
        Movie(
            id = "movie_2",
            title = "Accra Billionaire's Vow",
            posterUrl = "https://images.unsplash.com/photo-1478720568477-152d9b164e26?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A high-stakes Ghanaian romantic drama where an heir must choose between ancestral duty and true love in modern Accra. VJ Ice P delivers hilarious street-wise Luganda commentary.",
            genres = listOf("Romance", "Ghanaian", "Drama"),
            releaseYear = 2024,
            durationMinutes = 125,
            country = Country.GHANA,
            categories = listOf(Category.LATEST, Category.GHANA),
            vjId = "vj_ice_p",
            castIds = listOf("actor_genevieve", "actor_jim"),
            rating = 4.8f,
            isFeatured = true
        ),
        Movie(
            id = "movie_3",
            title = "Kibuli Royal Secret",
            posterUrl = "https://images.unsplash.com/photo-1518676599625-583562e84860?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?auto=format&fit=crop&q=80&w=1200",
            synopsis = "An authentic Ugandan thriller set in Kampala. When an ancient royal relic disappears from the palace, a young detective uncovers dark political conspiracies.",
            genres = listOf("Ugandan Original", "Mystery", "Thriller"),
            releaseYear = 2024,
            durationMinutes = 105,
            country = Country.UGANDA,
            categories = listOf(Category.LATEST, Category.UGANDA),
            vjId = null, // Original Ugandan film in Luganda
            castIds = emptyList(),
            rating = 4.7f,
            isFeatured = true
        ),
        Movie(
            id = "movie_4",
            title = "The Lagos Godfather",
            posterUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Tensions erupt when two underworld brotherhoods clash over control of the West African ports. Narrated with unmatched intensity by VJ Jingo.",
            genres = listOf("Nollywood", "Crime", "Action"),
            releaseYear = 2023,
            durationMinutes = 135,
            country = Country.NIGERIA,
            categories = listOf(Category.LATEST, Category.NIGERIA, Category.NEW_UPLOADS),
            vjId = "vj_jingo",
            castIds = listOf("actor_zubby", "actor_jim", "actor_rita"),
            rating = 4.9f,
            isFeatured = true
        ),

        // ─── New Uploads ──────────────────────────────────────────────────
        Movie(
            id = "movie_5",
            title = "Queen of Kumasi",
            posterUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1478720568477-152d9b164e26?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A courageous Ashanti woman rises against corrupt warlords to protect her village traditions. Narrated in Luganda by VJ Emmy.",
            genres = listOf("Ghanaian", "Historical", "Action"),
            releaseYear = 2024,
            durationMinutes = 110,
            country = Country.GHANA,
            categories = listOf(Category.NEW_UPLOADS, Category.GHANA),
            vjId = "vj_emmy",
            castIds = listOf("actor_genevieve", "actor_mercy"),
            rating = 4.6f
        ),
        Movie(
            id = "movie_6",
            title = "The Billionaire's Revenge",
            posterUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Framed for a crime he didn't commit, a tech genius rebuilds his life under a new identity to take down his betrayers. VJ Kevo delivers fast-paced narration.",
            genres = listOf("Action", "Nollywood", "Thriller"),
            releaseYear = 2024,
            durationMinutes = 120,
            country = Country.NIGERIA,
            categories = listOf(Category.NEW_UPLOADS, Category.NIGERIA),
            vjId = "vj_kevo",
            castIds = listOf("actor_ramsey", "actor_toyin"),
            rating = 4.5f
        ),
        Movie(
            id = "movie_7",
            title = "Bwaise Nights",
            posterUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1518676599625-583562e84860?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A gritty Ugandan neon noir following a night cab driver who stumbles onto a million-dollar diamond heist in Kampala.",
            genres = listOf("Ugandan Original", "Crime", "Drama"),
            releaseYear = 2024,
            durationMinutes = 98,
            country = Country.UGANDA,
            categories = listOf(Category.NEW_UPLOADS, Category.UGANDA),
            vjId = null,
            rating = 4.7f
        ),
        Movie(
            id = "movie_8",
            title = "Wedding Quarrel in Ikeja",
            posterUrl = "https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Hilarious Nollywood wedding comedy where rival families battle for prestige. VJ Ice P's commentary will leave you in stitches.",
            genres = listOf("Comedy", "Nollywood", "Romance"),
            releaseYear = 2024,
            durationMinutes = 105,
            country = Country.NIGERIA,
            categories = listOf(Category.NEW_UPLOADS, Category.NIGERIA),
            vjId = "vj_ice_p",
            castIds = listOf("actor_funke", "actor_odunlade", "actor_toyin"),
            rating = 4.8f
        ),
        Movie(
            id = "movie_9",
            title = "Gold Coast Heist",
            posterUrl = "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&q=80&w=1200",
            synopsis = "An elite crew of Ghanaian thieves plan the biggest gold vault robbery in West African history. Narrated in Luganda by VJ Mark.",
            genres = listOf("Ghanaian", "Heist", "Action"),
            releaseYear = 2024,
            durationMinutes = 115,
            country = Country.GHANA,
            categories = listOf(Category.NEW_UPLOADS, Category.GHANA),
            vjId = "vj_mark",
            castIds = listOf("actor_jim"),
            rating = 4.4f
        ),

        // ─── Classics ─────────────────────────────────────────────────────
        Movie(
            id = "movie_10",
            title = "Blood & Kingdom",
            posterUrl = "https://images.unsplash.com/photo-1518676599625-583562e84860?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1200",
            synopsis = "The legendary 2012 Nollywood epic about royal succession wars in ancient Igboland. Narrated by master VJ Junior.",
            genres = listOf("Classics", "Nollywood", "Royal Epic"),
            releaseYear = 2012,
            durationMinutes = 140,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_junior",
            castIds = listOf("actor_ramsey", "actor_genevieve", "actor_rita"),
            rating = 5.0f
        ),
        Movie(
            id = "movie_11",
            title = "The Last Princess of Ashanti",
            posterUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1478720568477-152d9b164e26?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Classic Ghanaian masterpiece telling the historical saga of Princess Yaa. Translated into Luganda by VJ Emmy.",
            genres = listOf("Classics", "Ghanaian", "Drama"),
            releaseYear = 2015,
            durationMinutes = 130,
            country = Country.GHANA,
            categories = listOf(Category.CLASSICS, Category.GHANA),
            vjId = "vj_emmy",
            rating = 4.9f
        ),
        Movie(
            id = "movie_12",
            title = "Sarafina of Jinja",
            posterUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A nostalgic Ugandan musical drama set along the Nile river during the 1980s. Pure Ugandan storytelling.",
            genres = listOf("Classics", "Ugandan Original", "Musical"),
            releaseYear = 2010,
            durationMinutes = 112,
            country = Country.UGANDA,
            categories = listOf(Category.CLASSICS, Category.UGANDA),
            vjId = null,
            rating = 4.8f
        ),
        Movie(
            id = "movie_13",
            title = "Dangerous Twins",
            posterUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=1200",
            synopsis = "The legendary twin swap thriller that captivated millions across Africa. Narrated by VJ Jingo.",
            genres = listOf("Classics", "Nollywood", "Thriller"),
            releaseYear = 2008,
            durationMinutes = 150,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_jingo",
            castIds = listOf("actor_ramsey", "actor_genevieve"),
            rating = 4.9f
        ),
        Movie(
            id = "movie_14",
            title = "Abuja Billionaire Club",
            posterUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Classic Nollywood luxury and secret societies drama. VJ Chris Edwards brings royal weight to the Luganda narration.",
            genres = listOf("Classics", "Nollywood", "Drama"),
            releaseYear = 2014,
            durationMinutes = 128,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_chris_edwards",
            castIds = listOf("actor_jim", "actor_mercy"),
            rating = 4.7f
        ),

        // ─── Ugandan Local Films ──────────────────────────────────────────
        Movie(
            id = "movie_15",
            title = "Kampala Hustle",
            posterUrl = "https://images.unsplash.com/photo-1518676599625-583562e84860?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Three young friends navigate the bustling street markets of downtown Kampala aiming to open their own music studio.",
            genres = listOf("Ugandan Original", "Drama", "Music"),
            releaseYear = 2023,
            durationMinutes = 100,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            rating = 4.6f
        ),
        Movie(
            id = "movie_16",
            title = "Pearl of Africa Warrior",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?auto=format&fit=crop&q=80&w=1200",
            synopsis = "High-energy Ugandan martial arts film produced in Wakaliga Kampala. Full of incredible local stunts and humor.",
            genres = listOf("Ugandan Original", "Action", "Martial Arts"),
            releaseYear = 2023,
            durationMinutes = 90,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            rating = 4.8f
        ),
        Movie(
            id = "movie_17",
            title = "Love in Entebbe",
            posterUrl = "https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1478720568477-152d9b164e26?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A heartwarming romance set along the serene beaches of Lake Victoria in Entebbe.",
            genres = listOf("Ugandan Original", "Romance", "Comedy"),
            releaseYear = 2024,
            durationMinutes = 105,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            rating = 4.5f
        ),
        Movie(
            id = "movie_18",
            title = "The Rwenzori Mystery",
            posterUrl = "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=1200",
            synopsis = "An expedition team climbing the Rwenzori Mountains uncovers ancient secrets hidden deep within the mist.",
            genres = listOf("Ugandan Original", "Adventure", "Thriller"),
            releaseYear = 2024,
            durationMinutes = 118,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            rating = 4.7f
        ),

        // ─── Nigerian (Nollywood VJ-Translated) ───────────────────────────
        Movie(
            id = "movie_19",
            title = "Chief's Secret Daughter",
            posterUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A long-lost daughter returns to claim her rightful seat on the board of a trillion-naira oil company. Luganda narration by VJ Junior.",
            genres = listOf("Nollywood", "Drama", "Family"),
            releaseYear = 2023,
            durationMinutes = 130,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_junior",
            castIds = listOf("actor_funke", "actor_mercy"),
            rating = 4.8f
        ),
        Movie(
            id = "movie_20",
            title = "Viper of Enugu",
            posterUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Unstoppable action thriller where an undercover operative takes down an international crime syndicate. VJ Kevo at full energy.",
            genres = listOf("Nollywood", "Action", "Crime"),
            releaseYear = 2024,
            durationMinutes = 110,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_kevo",
            castIds = listOf("actor_zubby", "actor_jim"),
            rating = 4.9f
        ),
        Movie(
            id = "movie_21",
            title = "Royal Wedding Scandal",
            posterUrl = "https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Secrets spill hours before the biggest royal wedding of the decade. Luganda narration by VJ Emmy.",
            genres = listOf("Nollywood", "Romance", "Drama"),
            releaseYear = 2023,
            durationMinutes = 122,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_emmy",
            castIds = listOf("actor_regina", "actor_ramsey"),
            rating = 4.6f
        ),
        Movie(
            id = "movie_22",
            title = "Superstar in Surulere",
            posterUrl = "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A struggling Afrobeat musician gets an unexpected chance at global stardom. Hilarious commentary by VJ Ice P.",
            genres = listOf("Nollywood", "Comedy", "Music"),
            releaseYear = 2024,
            durationMinutes = 115,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_ice_p",
            castIds = listOf("actor_odunlade", "actor_toyin"),
            rating = 4.7f
        ),

        // ─── Ghanaian (VJ-Translated) ─────────────────────────────────────
        Movie(
            id = "movie_23",
            title = "Fisherman's Promise",
            posterUrl = "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1478720568477-152d9b164e26?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A poignant coastal drama from Cape Coast about a young fisherman fighting to preserve his family's maritime legacy. Luganda by VJ Mark.",
            genres = listOf("Ghanaian", "Drama", "Family"),
            releaseYear = 2023,
            durationMinutes = 108,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_mark",
            rating = 4.6f
        ),
        Movie(
            id = "movie_24",
            title = "Diamonds of Tamale",
            posterUrl = "https://images.unsplash.com/photo-1518676599625-583562e84860?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1200",
            synopsis = "Fast-paced thriller following a team of prospectors who uncover illicit diamond deposits in northern Ghana. Luganda narration by VJ SMK.",
            genres = listOf("Ghanaian", "Action", "Thriller"),
            releaseYear = 2024,
            durationMinutes = 114,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_smk",
            rating = 4.5f
        ),
        Movie(
            id = "movie_25",
            title = "Accra Love Story",
            posterUrl = "https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&q=80&w=1200",
            synopsis = "A modern romantic comedy set in the trendy coffee shops and nightlife of Accra. Luganda narration by VJ Kriss Sweet.",
            genres = listOf("Ghanaian", "Rom-Com", "Romance"),
            releaseYear = 2024,
            durationMinutes = 102,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_kriss_sweet",
            rating = 4.7f
        ),
        Movie(
            id = "movie_26",
            title = "The Ashanti Code",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&q=80&w=600",
            backdropUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?auto=format&fit=crop&q=80&w=1200",
            synopsis = "An intellectual mystery drama tracking an ancient royal cipher passed down generations. Luganda by VJ Geoffrey.",
            genres = listOf("Ghanaian", "Mystery", "History"),
            releaseYear = 2023,
            durationMinutes = 120,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_geoffrey",
            rating = 4.8f
        )
    )

    fun getFeatured(): List<Movie> = movies.filter { it.isFeatured }
    fun getByCategory(cat: Category): List<Movie> = movies.filter { it.categories.contains(cat) }
    fun getByCountry(country: Country): List<Movie> = movies.filter { it.country == country }
    fun getByVj(vjId: String): List<Movie> = movies.filter { it.vjId == vjId }
    fun getById(id: String): Movie? = movies.find { it.id == id }
    fun search(
        query: String,
        vjId: String? = null,
        category: Category? = null,
        country: Country? = null
    ): List<Movie> {
        return movies.filter { movie ->
            val matchesQuery = query.isEmpty() ||
                    movie.title.contains(query, ignoreCase = true) ||
                    movie.synopsis.contains(query, ignoreCase = true) ||
                    movie.genres.any { it.contains(query, ignoreCase = true) }

            val matchesVj = vjId == null || movie.vjId == vjId
            val matchesCategory = category == null || movie.categories.contains(category)
            val matchesCountry = country == null || movie.country == country

            matchesQuery && matchesVj && matchesCategory && matchesCountry
        }
    }
}
