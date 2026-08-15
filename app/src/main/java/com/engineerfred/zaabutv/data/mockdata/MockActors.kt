package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.domain.model.Country

object MockActors {
    val actors = listOf(
        Actor(
            id = "actor_funke",
            name = "Funke Akindele",
            photoUrl = "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?auto=format&fit=crop&q=80&w=400",
            bio = "Nollywood superstar, director, and creator of the legendary 'Jenifa' series.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_ramsey",
            name = "Ramsey Nouah",
            photoUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&q=80&w=400",
            bio = "Veteran Nollywood actor and director known as the king of African romantic films.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_genevieve",
            name = "Genevieve Nnaji",
            photoUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&q=80&w=400",
            bio = "Iconic Nollywood actress and director of Netflix's first Nigerian original 'Lionheart'.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_mercy",
            name = "Mercy Johnson",
            photoUrl = "https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?auto=format&fit=crop&q=80&w=400",
            bio = "Award-winning Nollywood actress famous for versatile emotional & comedic roles.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_jim",
            name = "Jim Iyke",
            photoUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&q=80&w=400",
            bio = "Nollywood's quintessential bad boy actor, producer, and entrepreneur.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_rita",
            name = "Rita Dominic",
            photoUrl = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&q=80&w=400",
            bio = "Africa Movie Academy Award-winning actress and film producer.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_zubby",
            name = "Zubby Michael",
            photoUrl = "https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?auto=format&fit=crop&q=80&w=400",
            bio = "High-energy Nollywood action hero and crowd favorite across East Africa.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_regina",
            name = "Regina Daniels",
            photoUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&q=80&w=400",
            bio = "Popular Nollywood actress and film producer.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_odunlade",
            name = "Odunlade Adekola",
            photoUrl = "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?auto=format&fit=crop&q=80&w=400",
            bio = "Prolific Nollywood actor, singer, and king of hilarious movie memes.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_toyin",
            name = "Toyin Abraham",
            photoUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&q=80&w=400",
            bio = "Box-office queen of Nollywood comedy blockbusters.",
            country = Country.NIGERIA
        )
    )

    fun getById(id: String): Actor? = actors.find { it.id == id }
    fun getByIds(ids: List<String>): List<Actor> = actors.filter { ids.contains(it.id) }
}
