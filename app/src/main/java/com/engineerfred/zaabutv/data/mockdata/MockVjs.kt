package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Vj

object MockVjs {
    val vjs = listOf(
        Vj(
            id = "vj_junior",
            name = "VJ Junior",
            photoUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&q=80&w=400",
            bio = "Uganda's pioneer Video Jockey (Marysmarts Matovu) with over 15 years of narrating action, blockbusters, and Nollywood hits in Luganda. Known for signature catchphrases and dramatic suspense.",
            movieCount = 142,
            specialties = listOf("Action", "Nollywood Thrillers", "Epic Drama")
        ),
        Vj(
            id = "vj_ice_p",
            name = "VJ Ice P",
            photoUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&q=80&w=400",
            bio = "Master of high-energy comedy and street Luganda slang. VJ Ice P brings Ghanaian & Nollywood comedies to life with unparalleled humor.",
            movieCount = 98,
            specialties = listOf("Comedy", "Ghanaian Cinema", "Urban Romance")
        ),
        Vj(
            id = "vj_emmy",
            name = "VJ Emmy",
            photoUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&q=80&w=400",
            bio = "Renowned for emotional story translations and deep family dramas. Emmy's poetic Luganda narration resonates with audiences of all ages.",
            movieCount = 85,
            specialties = listOf("Family Drama", "Romance", "Nollywood Classics")
        ),
        Vj(
            id = "vj_jingo",
            name = "VJ Jingo",
            photoUrl = "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?auto=format&fit=crop&q=80&w=400",
            bio = "Legendary Luganda translator famous for classic cinema and intense crime thrillers. A household voice across Kampala.",
            movieCount = 110,
            specialties = listOf("Crime Thrillers", "Classics", "Action")
        ),
        Vj(
            id = "vj_kevo",
            name = "VJ Kevo",
            photoUrl = "https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?auto=format&fit=crop&q=80&w=400",
            bio = "Fast-talking VJ specializing in martial arts and explosive Nollywood revenge dramas. High intensity guaranteed.",
            movieCount = 76,
            specialties = listOf("Martial Arts", "Revenge Dramas", "Nollywood Action")
        ),
        Vj(
            id = "vj_mark",
            name = "VJ Mark",
            photoUrl = "https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?auto=format&fit=crop&q=80&w=400",
            bio = "Known for smooth voiceovers and detailed cultural commentary explaining West African traditions to East African fans.",
            movieCount = 64,
            specialties = listOf("Cultural Drama", "Ghanaian Romance", "Mystery")
        ),
        Vj(
            id = "vj_ulio",
            name = "VJ Ulio",
            photoUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&q=80&w=400",
            bio = "The horror & supernatural thriller specialist. VJ Ulio delivers chilling Luganda narration for African folklore horror.",
            movieCount = 52,
            specialties = listOf("Horror", "Folklore", "Supernatural Thrillers")
        ),
        Vj(
            id = "vj_josh_k",
            name = "VJ Josh K",
            photoUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?auto=format&fit=crop&q=80&w=400",
            bio = "Young dynamic VJ taking social media by storm. Fresh Luganda slang paired with modern Nollywood blockbusters.",
            movieCount = 45,
            specialties = listOf("Modern Nollywood", "Rom-Coms", "Youth Cinema")
        ),
        Vj(
            id = "vj_smk",
            name = "VJ SMK",
            photoUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&q=80&w=400",
            bio = "Crisp audio clarity and suspenseful pacing. VJ SMK makes every high-stakes movie feel like a stadium event.",
            movieCount = 60,
            specialties = listOf("Suspense", "Polotical Drama", "Thrillers")
        ),
        Vj(
            id = "vj_chris_edwards",
            name = "VJ Chris Edwards",
            photoUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?auto=format&fit=crop&q=80&w=400",
            bio = "Deep resonant baritone voice with a flair for epic royal African kingdom sagas.",
            movieCount = 70,
            specialties = listOf("Royal Sagas", "Historical Drama", "Nollywood Epics")
        ),
        Vj(
            id = "vj_geoffrey",
            name = "VJ Geoffrey",
            photoUrl = "https://images.unsplash.com/photo-1501196354995-cbb51c65aaea?auto=format&fit=crop&q=80&w=400",
            bio = "Master narrator for investigative crime and legal dramas in Nollywood & Ghanaian cinema.",
            movieCount = 58,
            specialties = listOf("Legal Drama", "Investigative", "Classics")
        ),
        Vj(
            id = "vj_kriss_sweet",
            name = "VJ Kriss Sweet",
            photoUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&q=80&w=400",
            bio = "Warm, engaging storytelling style specializing in romance and emotional family sagas.",
            movieCount = 67,
            specialties = listOf("Romance", "Family Sagas", "Ghanaian Hits")
        )
    )

    fun getById(id: String): Vj? = vjs.find { it.id == id }
}
