package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Category
import com.engineerfred.zaabutv.domain.model.Country
import com.engineerfred.zaabutv.domain.model.Movie

// NOTE: posterUrl / backdropUrl below are Unsplash placeholders — swap each one
// for the real poster/backdrop of the matching title once you've searched it up.
object MockMovies {
    val movies = listOf(
        // Featured / Hero Carousel
        Movie(
            id = "movie_1",
            title = "Gangs of Lagos",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BOGIxNTc3ZDAtMjdhNi00YWYwLTg1MjgtNzM2NjhkNDhhMTljXkEyXkFqcGc@._V1_.jpg",
            backdropUrl = "https://m.media-amazon.com/images/S/pv-target-images/778dca26c7efef302e6ed5f40ff80b9f219cd29138c487705ab8059d57b0cb1f.jpg",
            synopsis = "A gritty crime saga following childhood friends from Isale Eko as their loyalty is tested by politics, betrayal, and turf wars in Lagos. Translated into explosive Luganda by VJ SMK.",
            genres = listOf("Action", "Nollywood", "Crime Drama"),
            releaseYear = 2023,
            durationMinutes = 128,
            country = Country.NIGERIA,
            categories = listOf(Category.LATEST, Category.NIGERIA),
            vjId = "vj_smk",
            vjName = "VJ SMK",
            castIds = listOf("actor_ramsey", "actor_zubby", "actor_funke"),
            rating = 4.9f,
            isFeatured = true
        ),
        Movie(
            id = "movie_2",
            title = "A Taste of Sin",
            posterUrl = "https://images.plex.tv/photo?size=large-1280&url=https%3A%2F%2Fmetadata-static.plex.tv%2Fd%2Fgracenote%2Fde3a07bdd98f95e395dd78cfacda723a.jpg",
            backdropUrl = "https://occ-0-8407-2219.1.nflxso.net/dnm/api/v6/6AYY37jfdO6hpXcMjf9Yu5cnmO0/AAAABRNS3p3M0I4eoN-XJnUYIddrEy90iNLQdw63yf7ZycpjQumF1uQu2ngsThaODYipawFehEO4EArZNlXwXizU2bduPiGQ9mL0bLJi.jpg?r=e40",
            synopsis = "A Ghanaian drama exploring faith, temptation, and redemption through two pastors pulled in very different directions. VJ Kiwa delivers hilarious street-wise Luganda commentary.",
            genres = listOf("Drama", "Ghanaian"),
            releaseYear = 2023,
            durationMinutes = 112,
            country = Country.GHANA,
            categories = listOf(Category.LATEST, Category.GHANA),
            vjId = "vj_kiwa",
            vjName = "VJ Kiwa",
            castIds = listOf("actor_genevieve", "actor_jim"),
            rating = 4.8f,
            isFeatured = true
        ),
        Movie(
            id = "movie_3",
            title = "Queen of Katwe",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/9/90/Queen_of_Katwe_poster.jpg?utm_source=en.wikipedia.org&utm_campaign=index&utm_content=original",
            backdropUrl = "https://i.ytimg.com/vi/poEdA5WvfAw/maxresdefault.jpg",
            synopsis = "The true story of a girl from the Katwe slum of Kampala who becomes a chess prodigy and finds a path out of poverty through the game.",
            genres = listOf("Ugandan Original", "Drama", "Biography"),
            releaseYear = 2016,
            durationMinutes = 124,
            country = Country.UGANDA,
            categories = listOf(Category.LATEST, Category.UGANDA),
            vjId = null, // Original Ugandan story, no translation needed
            vjName = null,
            castIds = emptyList(),
            rating = 4.7f,
            isFeatured = true
        ),
        Movie(
            id = "movie_4",
            title = "A Tribe Called Judah",
            posterUrl = "https://substackcdn.com/image/fetch/\$s_!Zxk6!,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F3a0585f9-51cc-4e03-8a56-e643197b9ad5.heic",
            backdropUrl = "https://i.ytimg.com/vi/fnnNA5-NPUk/maxresdefault.jpg",
            synopsis = "A single mother raises five sons with very different personalities who must band together to pull off a heist for her. Narrated with unmatched energy by VJ Jingo.",
            genres = listOf("Nollywood", "Comedy", "Family"),
            releaseYear = 2023,
            durationMinutes = 130,
            country = Country.NIGERIA,
            categories = listOf(Category.LATEST, Category.NIGERIA, Category.NEW_UPLOADS),
            vjId = "vj_jingo",
            vjName = "VJ Jingo",
            castIds = listOf("actor_zubby", "actor_jim", "actor_rita"),
            rating = 4.9f,
            isFeatured = true
        ),

        // New Uploads
        Movie(
            id = "movie_5",
            title = "Sidechic Gang",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BYjFiZmUzYzgtODUyZi00NzMwLWFiYWYtNWI1MWFhMjhkNTg1XkEyXkFqcGc@._V1_.jpg",
            backdropUrl = "https://i.ytimg.com/vi/sGPc8WtXT8w/maxresdefault.jpg",
            synopsis = "A group of women scorned by unfaithful men band together for payback in this sharp Ghanaian comedy-drama. Narrated in Luganda by VJ Josh K.",
            genres = listOf("Ghanaian", "Comedy", "Drama"),
            releaseYear = 2018,
            durationMinutes = 105,
            country = Country.GHANA,
            categories = listOf(Category.NEW_UPLOADS, Category.GHANA),
            vjId = "vj_josh_k",
            vjName = "VJ Josh K",
            castIds = listOf("actor_genevieve", "actor_mercy"),
            rating = 4.6f
        ),
        Movie(
            id = "movie_6",
            title = "Jagun Jagun",
            posterUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzxv1k-WvXFyaaJ4j9r9tGt4TecDO3gWt-HKA5KHy4K5XUbb0-74Z7hmKL&s=10",
            backdropUrl = "https://m.media-amazon.com/images/M/MV5BOGNlYTA4ZDgtNWRkOS00ZDAxLTk5N2ItNjlhNDNlYjAyMmMzXkEyXkFqcGc@._V1_.jpg",
            synopsis = "An epic tale of warlords and warriors in old Yorubaland, where a young fighter is trained by a ruthless master only to turn against him. VJ Kevo delivers fast-paced narration.",
            genres = listOf("Action", "Nollywood", "Epic"),
            releaseYear = 2023,
            durationMinutes = 149,
            country = Country.NIGERIA,
            categories = listOf(Category.NEW_UPLOADS, Category.NIGERIA),
            vjId = "vj_kevo",
            vjName = "VJ Kevo",
            castIds = listOf("actor_ramsey", "actor_toyin"),
            rating = 4.5f
        ),
        Movie(
            id = "movie_7",
            title = "Rolex Time",
            posterUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS41LZuRHW1hUghvxGVj1iJKrnC7OgCmsEyDju0ezEDTe-ZHW9bEgg8VhRq&s=10",
            backdropUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS41LZuRHW1hUghvxGVj1iJKrnC7OgCmsEyDju0ezEDTe-ZHW9bEgg8VhRq&s=10",
            synopsis = "A high-energy Wakaliwood action film out of Kampala, packed with the studio's trademark homemade stunts and commando chaos.",
            genres = listOf("Ugandan Original", "Action", "Martial Arts"),
            releaseYear = 2025,
            durationMinutes = 89,
            country = Country.UGANDA,
            categories = listOf(Category.NEW_UPLOADS, Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.7f
        ),
        Movie(
            id = "movie_8",
            title = "Everybody Loves Jenifa",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BYjRlMmM0MTQtNzViOS00Y2U5LWE0MWMtMzM0ZmU5ZDEyZTcwXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            backdropUrl = "https://m.media-amazon.com/images/S/pv-target-images/081cabc43e148a7409de61c2c81c5caea47828d45d475e0d2b8459f97e1fd6ae.jpg",
            synopsis = "The beloved Jenifa steps into a new chapter as a humanitarian, reconnecting with old friends along the way. VJ Kiwa's commentary will leave you in stitches.",
            genres = listOf("Comedy", "Nollywood", "Drama"),
            releaseYear = 2024,
            durationMinutes = 108,
            country = Country.NIGERIA,
            categories = listOf(Category.NEW_UPLOADS, Category.NIGERIA),
            vjId = "vj_kiwa",
            vjName = "VJ Kiwa",
            castIds = listOf("actor_funke", "actor_odunlade", "actor_toyin"),
            rating = 4.8f
        ),
        Movie(
            id = "movie_9",
            title = "Azali",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMTI4ODg5MmYtNzM2MS00YTk0LTllYzUtMTNmOTQyMjY3MGEzXkEyXkFqcGc@._V1_.jpg",
            backdropUrl = "https://m.media-amazon.com/images/M/MV5BMTI4ODg5MmYtNzM2MS00YTk0LTllYzUtMTNmOTQyMjY3MGEzXkEyXkFqcGc@._V1_.jpg",
            synopsis = "A tense Ghanaian drama-thriller following a young woman whose past comes back to threaten the life she's built. Narrated in Luganda by VJ Mark.",
            genres = listOf("Ghanaian", "Drama", "Thriller"),
            releaseYear = 2019,
            durationMinutes = 101,
            country = Country.GHANA,
            categories = listOf(Category.NEW_UPLOADS, Category.GHANA),
            vjId = "vj_mark",
            vjName = "VJ Mark",
            castIds = listOf("actor_jim"),
            rating = 4.4f
        ),

        // Classics
        Movie(
            id = "movie_10",
            title = "Living in Bondage",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BNTMzYzYwZDMtNjRmNi00MzYwLThkOWMtNzVjOTUzNWQ1ZGQyXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            backdropUrl = "https://m.media-amazon.com/images/M/MV5BOTJlNmIyNWEtNGYyNS00YWZmLWJhMDItODM5MzZhNmE5Nzc4XkEyXkFqcGc@._V1_.jpg",
            synopsis = "The landmark film credited with launching the Nollywood home-video era, about a man who trades everything for wealth through a dangerous cult pact. Narrated by master VJ Chris Edwards.",
            genres = listOf("Classics", "Nollywood", "Drama"),
            releaseYear = 1992,
            durationMinutes = 130,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_chris_edwards",
            vjName = "VJ Chris Edwards",
            castIds = listOf("actor_ramsey", "actor_genevieve", "actor_rita"),
            rating = 5.0f
        ),
        Movie(
            id = "movie_11",
            title = "Love Brewed in the African Pot",
            posterUrl = "https://urbaneafrica.wordpress.com/wp-content/uploads/2014/11/love-brewed-in-an-african-pot.png",
            backdropUrl = "https://thebrokencliche.wordpress.com/wp-content/uploads/2021/12/love-brewed.png",
            synopsis = "Ghana's pioneering independent feature film, a romance caught between colonial-era class expectations and true love. Translated into Luganda by VJ Kriss Sweet.",
            genres = listOf("Classics", "Ghanaian", "Drama", "Romance"),
            releaseYear = 1981,
            durationMinutes = 105,
            country = Country.GHANA,
            categories = listOf(Category.CLASSICS, Category.GHANA),
            vjId = "vj_kriss_sweet",
            vjName = "VJ Kriss Sweet",
            rating = 4.9f
        ),
        Movie(
            id = "movie_12",
            title = "Who Killed Captain Alex?",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMmE2M2U4MGQtZjFhYi00MTZiLTkxNWUtYzE2YTAwZTM4ZDg4XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            backdropUrl = "https://m.media-amazon.com/images/S/pv-target-images/0d3bfa79327c87f6408db3c1d4a4c4ff01c62fdb54eb383679a5f63ee7520f43._UR1920,1080_CLs%7C1920,1080%7C/G/bundle/BottomRightCardGradient16x9.png,/G/01/digital/video/merch/subs/benefit-id/a-f/cineverseus/logos/channels-logo-white.png%7C0,0,1920,1080+0,0,1920,1080+1487,885,354,117_kvb77f88a9302b69ab2ef7033330c8dea5_SX624_FMjpg_.jpg",
            synopsis = "The ultra-low-budget action classic that put Wakaliwood on the map — Uganda's army takes on a ruthless mafia after a commander's murder.",
            genres = listOf("Classics", "Ugandan Original", "Action"),
            releaseYear = 2010,
            durationMinutes = 68,
            country = Country.UGANDA,
            categories = listOf(Category.CLASSICS, Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.8f
        ),
        Movie(
            id = "movie_13",
            title = "King of Boys",
            posterUrl = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p20487596_b_v13_aa.jpg",
            backdropUrl = "https://thenativemag.com/wp-content/uploads/2021/08/King-Of-Boys.jpg",
            synopsis = "A powerful Lagos businesswoman with a hidden criminal past sets her sights on political office, and the underworld she once ruled resurfaces. Narrated by VJ Jingo.",
            genres = listOf("Classics", "Nollywood", "Crime", "Political Drama"),
            releaseYear = 2018,
            durationMinutes = 169,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_jingo",
            vjName = "VJ Jingo",
            castIds = listOf("actor_ramsey", "actor_genevieve"),
            rating = 4.9f
        ),
        Movie(
            id = "movie_14",
            title = "Lionheart",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/c/c8/Lionheart_%282018_film%29_poster.jpg?utm_source=en.wikipedia.org&utm_campaign=index&utm_content=original",
            backdropUrl = "https://womentainment.com/wp-content/uploads/2019/01/lionheart-netflix-1200x676.jpg",
            synopsis = "A daughter steps up to save her father's struggling transport company from rival takeover attempts and her own family's doubts. VJ Chris Edwards brings warmth to the Luganda narration.",
            genres = listOf("Classics", "Nollywood", "Drama", "Comedy"),
            releaseYear = 2018,
            durationMinutes = 95,
            country = Country.NIGERIA,
            categories = listOf(Category.CLASSICS, Category.NIGERIA),
            vjId = "vj_chris_edwards",
            vjName = "VJ Chris Edwards",
            castIds = listOf("actor_jim", "actor_mercy"),
            rating = 4.7f
        ),

        // Ugandan Local Films
        Movie(
            id = "movie_15",
            title = "Bad Black",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BNWE5Mjk1NTQtZDgwNS00N2ExLWFkNDctMDBkMTRjNzc0OTc1XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            backdropUrl = "https://i.ytimg.com/vi/5MHmzj086ww/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA2o0GBhCjvp8tozTb-7joYanErug",
            synopsis = "A Wakaliwood action-comedy about an orphaned street girl who grows into a fierce, gun-wielding force in the slums of Kampala.",
            genres = listOf("Ugandan Original", "Action", "Comedy"),
            releaseYear = 2016,
            durationMinutes = 68,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.6f
        ),
        Movie(
            id = "movie_16",
            title = "Boda Boda Thieves",
            posterUrl = "https://sdinet.org/wp-content/uploads/2015/01/bodabodaposter_medium.jpg",
            backdropUrl = "https://qqcdnpictest.mxplay.com/pic/deb84177861e593d97cf35f77feaa75f/en/16x9/640x360/test_pic1622186055267.jpg",
            synopsis = "A teenage boda boda rider must track down his family's stolen motorcycle before his father returns, or lose their only source of income.",
            genres = listOf("Ugandan Original", "Drama"),
            releaseYear = 2015,
            durationMinutes = 82,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.8f
        ),
        Movie(
            id = "movie_17",
            title = "State Research Bureau",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BZDc2N2Q2MTgtMGJkNi00MjM0LWE5ZDQtODYyMDU2YWFjMzA5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
            backdropUrl = "https://resizing.flixster.com/V_jemENEGdJ-qjf5uzwnlL8X-o4=/fit-in/352x330/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p25839113_i_h10_aa.jpg",
            synopsis = "A historical drama set during the brutal Idi Amin era, centered on the notorious SRB and the ordinary lives caught in its shadow.",
            genres = listOf("Ugandan Original", "Historical", "Drama"),
            releaseYear = 2011,
            durationMinutes = 96,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.5f
        ),
        Movie(
            id = "movie_18",
            title = "Imperial Blue",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BOTRkNTA1M2QtYTRkNy00MmFhLThhYzAtMjkwYTQxOWVhN2ZiXkEyXkFqcGc@._V1_.jpg",
            backdropUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcxbdcGlQeSqlOJSa0b4a_aHK0rfddxBIFd6aIoXixFjiXNGK6H2vR0rE-&s=10",
            synopsis = "A Ugandan drama following a family navigating ambition, class, and loyalty across a fast-changing Kampala.",
            genres = listOf("Ugandan Original", "Drama"),
            releaseYear = 2019,
            durationMinutes = 100,
            country = Country.UGANDA,
            categories = listOf(Category.UGANDA),
            vjId = null,
            vjName = null,
            rating = 4.7f
        ),

        // Nigerian (Nollywood VJ-Translated)
        Movie(
            id = "movie_19",
            title = "Anikulapo",
            posterUrl = "https://i0.wp.com/media.premiumtimesng.com/wp-content/files/2024/03/Anikulapo-1.jpeg?resize=468%2C656&ssl=1",
            backdropUrl = "https://lifestyle.thecable.ng/wp-content/uploads/2024/03/Anikulapo.jpg",
            synopsis = "A wandering weaver is granted the power to raise the dead by a mystical bird, and pays a heavy price for the gift in this Yoruba epic. Luganda narration by VJ Aaron.",
            genres = listOf("Nollywood", "Epic", "Fantasy"),
            releaseYear = 2022,
            durationMinutes = 155,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_aaron",
            vjName = "VJ Aaron",
            castIds = listOf("actor_funke", "actor_mercy"),
            rating = 4.8f
        ),
        Movie(
            id = "movie_20",
            title = "The Black Book",
            posterUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcB_wVpLtplyApiz5B5x_WFV2KwcMijCvgJ9_Q9lg_LYNA-wO9XiaEM50&s=10",
            backdropUrl = "https://resizing.flixster.com/BW_mwMOq0eJi6SjEpMTFE8rzwdo=/620x336/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p25488487_i_h8_aa.jpg",
            synopsis = "A grieving deacon turned vigilante uncovers a web of police corruption while hunting the men who killed his son. VJ Kevo at full energy.",
            genres = listOf("Nollywood", "Action", "Crime"),
            releaseYear = 2023,
            durationMinutes = 124,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_kevo",
            vjName = "VJ Kevo",
            castIds = listOf("actor_zubby", "actor_jim"),
            rating = 4.9f
        ),
        Movie(
            id = "movie_21",
            title = "The Wedding Party",
            posterUrl = "https://pearlinebycharlemagne.wordpress.com/wp-content/uploads/2019/11/381c0188cbbeb5be86214cc7fcd7d63895c7890d.jpg?w=426&h=450&crop=1",
            backdropUrl = "https://variety.com/wp-content/uploads/2016/09/the-wedding-party.jpg",
            synopsis = "Chaos erupts behind the scenes of a lavish Lagos wedding as two families and their quirks collide on the big day. Luganda narration by VJ Jovan.",
            genres = listOf("Nollywood", "Romance", "Comedy"),
            releaseYear = 2016,
            durationMinutes = 105,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_jovan",
            vjName = "VJ Jovan",
            castIds = listOf("actor_regina", "actor_ramsey"),
            rating = 4.6f
        ),
        Movie(
            id = "movie_22",
            title = "Brotherhood",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMDVmNjA5MjMtZGY3Mi00NTNiLTgwODAtMjgwNjI4MzZiMTc3XkEyXkFqcGc@._V1_.jpg",
            backdropUrl = "https://i0.wp.com/media.premiumtimesng.com/wp-content/files/2022/10/Tobi-Bakre-and-Falz-are-two-brothers-on-opposite-side-of-the-law-in-Brotherhood-movie.jpeg?resize=790%2C430&ssl=1",
            synopsis = "Two brothers on opposite sides of the law are pulled into a dangerous heist that tests blood ties against loyalty to the streets. Hilarious commentary by VJ Kiwa.",
            genres = listOf("Nollywood", "Action", "Crime"),
            releaseYear = 2022,
            durationMinutes = 122,
            country = Country.NIGERIA,
            categories = listOf(Category.NIGERIA),
            vjId = "vj_kiwa",
            vjName = "VJ Kiwa",
            castIds = listOf("actor_odunlade", "actor_toyin"),
            rating = 4.7f
        ),

        // Ghanaian (VJ-Translated)
        Movie(
            id = "movie_23",
            title = "Deadly Voyage",
            posterUrl = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p17929_p_v8_aa.jpg",
            backdropUrl = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p17929_p_v8_aa.jpg",
            synopsis = "Based on true events, a young Ghanaian stows away aboard a cargo ship in search of a better life, only to face a harrowing voyage. Luganda by VJ Mark.",
            genres = listOf("Ghanaian", "Drama"),
            releaseYear = 1996,
            durationMinutes = 96,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_mark",
            vjName = "VJ Mark",
            rating = 4.6f
        ),
        Movie(
            id = "movie_24",
            title = "A Sting in a Tale",
            posterUrl = "https://nollywire.com/wp-content/uploads/2023/04/A-Sting-In-A-Tale-2021-Nollywire.jpg",
            backdropUrl = "https://nollywire.com/wp-content/uploads/2023/04/A-Sting-In-A-Tale-2021-Nollywire.jpg",
            synopsis = "A sharp drama following graduates in Accra as unemployment and social pressure push them toward desperate choices. Luganda narration by VJ SMK.",
            genres = listOf("Ghanaian", "Drama", "Thriller"),
            releaseYear = 2009,
            durationMinutes = 115,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_smk",
            vjName = "VJ SMK",
            rating = 4.5f
        ),
        Movie(
            id = "movie_25",
            title = "Beyonce: The President's Daughter",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMjMwMTUyODI1NV5BMl5BanBnXkFtZTgwODA2NzAwNzE@._V1_.jpg",
            backdropUrl = "https://i.ytimg.com/vi/uKXTfK9RH4U/maxresdefault.jpg",
            synopsis = "The headstrong daughter of Ghana's president causes a stir at boarding school while navigating privilege, rebellion, and first love. Luganda narration by VJ Kriss Sweet.",
            genres = listOf("Ghanaian", "Drama"),
            releaseYear = 2018,
            durationMinutes = 108,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_kriss_sweet",
            vjName = "VJ Kriss Sweet",
            rating = 4.7f
        ),
        Movie(
            id = "movie_26",
            title = "Potato Potahto",
            posterUrl = "https://occ-0-6207-299.1.nflxso.net/dnm/api/v6/mAcAr9TxZIVbINe88xb3Teg5_OA/AAAABYKkKGNBIVUDB7ce0gTAgx3NnHvjFHJx0MPq_3S4kpNh4JhqN_BnnHw0CIGQMjoVw4iJsWiqM-T7KiB_ydiX6ev7h9MYcU4FRCCL.jpg?r=fc3",
            backdropUrl = "https://m.media-amazon.com/images/M/MV5BNTY3ODA4YTItZWU4YS00MGU3LTgyOWYtMDhkYmNlMmE1MzIwXkEyXkFqcGc@._V1_.jpg",
            synopsis = "A divorced couple's new marriages collide when their blended families end up living under one roof. Luganda by VJ Geoffrey.",
            genres = listOf("Ghanaian", "Comedy", "Drama"),
            releaseYear = 2017,
            durationMinutes = 104,
            country = Country.GHANA,
            categories = listOf(Category.GHANA),
            vjId = "vj_geoffrey",
            vjName = "VJ Geoffrey",
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