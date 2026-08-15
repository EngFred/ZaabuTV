package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Vj

object MockVjs {
    val vjs = listOf(
        Vj(
            id = "vj_smk",
            name = "VJ SMK",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH_2kYxNek8Lgu_BL2NXl83U8beWlsUCOtPEKQohQCLQIXWd-3rlYyAYc&s=10",
            bio = "Uganda's pioneer Video Jockey (Marysmarts Matovu) with over 15 years of narrating action, blockbusters, and Nollywood hits in Luganda. Known for signature catchphrases and dramatic suspense.",
            movieCount = 142,
            specialties = listOf("Action", "Nollywood Thrillers", "Epic Drama")
        ),
        Vj(
            id = "vj_kiwa",
            name = "VJ Kiwa",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqhIOsQuICus0FPmvjMRErZghNsbqJ6QYMrAQXLKh0jhIbxx_yB4NXZqzo&s=10",
            bio = "Master of high-energy comedy and street Luganda slang. VJ Ice P brings Ghanaian & Nollywood comedies to life with unparalleled humor.",
            movieCount = 98,
            specialties = listOf("Comedy", "Ghanaian Cinema", "Urban Romance")
        ),
        Vj(
            id = "vj_jovan",
            name = "VJ Jovan",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "Renowned for emotional story translations and deep family dramas. Emmy's poetic Luganda narration resonates with audiences of all ages.",
            movieCount = 85,
            specialties = listOf("Family Drama", "Romance", "Nollywood Classics")
        ),
        Vj(
            id = "vj_jingo",
            name = "VJ Jingo",
            photoUrl = "https://i1.sndcdn.com/artworks-000076334568-jg4pl4-t500x500.jpg",
            bio = "Legendary Luganda translator famous for classic cinema and intense crime thrillers. A household voice across Kampala.",
            movieCount = 110,
            specialties = listOf("Crime Thrillers", "Classics", "Action")
        ),
        Vj(
            id = "vj_kevo",
            name = "VJ Kevo",
            photoUrl = "https://pbs.twimg.com/media/Farz3LuXgAACcP6.jpg",
            bio = "Fast-talking VJ specializing in martial arts and explosive Nollywood revenge dramas. High intensity guaranteed.",
            movieCount = 76,
            specialties = listOf("Martial Arts", "Revenge Dramas", "Nollywood Action")
        ),
        Vj(
            id = "vj_mark",
            name = "VJ Mark",
            photoUrl = "https://portal.naraboxtv.com/storage/vjs/profiles/01KJK1P00C7EXD658KAF2NKBQN.jpeg",
            bio = "Known for smooth voiceovers and detailed cultural commentary explaining West African traditions to East African fans.",
            movieCount = 64,
            specialties = listOf("Cultural Drama", "Ghanaian Romance", "Mystery")
        ),
        Vj(
            id = "vj_aaron",
            name = "VJ Aaron",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "The horror & supernatural thriller specialist. VJ Ulio delivers chilling Luganda narration for African folklore horror.",
            movieCount = 52,
            specialties = listOf("Horror", "Folklore", "Supernatural Thrillers")
        ),
        Vj(
            id = "vj_josh_k",
            name = "VJ Josh K",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "Young dynamic VJ taking social media by storm. Fresh Luganda slang paired with modern Nollywood blockbusters.",
            movieCount = 45,
            specialties = listOf("Modern Nollywood", "Rom-Coms", "Youth Cinema")
        ),
        Vj(
            id = "vj_chris_edwards",
            name = "VJ Chris Edwards",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "Deep resonant baritone voice with a flair for epic royal African kingdom sagas.",
            movieCount = 70,
            specialties = listOf("Royal Sagas", "Historical Drama", "Nollywood Epics")
        ),
        Vj(
            id = "vj_geoffrey",
            name = "VJ Geoffrey",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "Master narrator for investigative crime and legal dramas in Nollywood & Ghanaian cinema.",
            movieCount = 58,
            specialties = listOf("Legal Drama", "Investigative", "Classics")
        ),
        Vj(
            id = "vj_kriss_sweet",
            name = "VJ Kriss Sweet",
            photoUrl = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg",
            bio = "Warm, engaging storytelling style specializing in romance and emotional family sagas.",
            movieCount = 67,
            specialties = listOf("Romance", "Family Sagas", "Ghanaian Hits")
        )
    )

    fun getById(id: String): Vj? = vjs.find { it.id == id }
}
