package com.engineerfred.zaabutv.data.mockdata

import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.domain.model.Country

object MockActors {
    val actors = listOf(
        Actor(
            id = "actor_funke",
            name = "Funke Akindele",
            photoUrl = "https://yt3.googleusercontent.com/YWF77jE3MCUhmvb7OclZQnN7ZSMI_WuufbNGLeoAYXdol71RmcqxKECdyWfKyIxv6px94fl14A=s900-c-k-c0x00ffffff-no-rj",
            bio = "Nollywood superstar, director, and creator of the legendary 'Jenifa' series.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_ramsey",
            name = "Ramsey Nouah",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrjnLbIF8auyhyGPyI_Uw2AIlxeCquB0iNxjJv_CFF0AjANch33o_ntVY&s=10",
            bio = "Veteran Nollywood actor and director known as the king of African romantic films.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_genevieve",
            name = "Genevieve Nnaji",
            photoUrl = "https://images.prismic.io/teclone-clacified/581dd5d7-e40d-4d54-9c2b-cce89a03cd61_Genevieve+Nnaji.jpeg?auto=format%2Ccompress&q=75&w=500",
            bio = "Iconic Nollywood actress and director of Netflix's first Nigerian original 'Lionheart'.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_mercy",
            name = "Mercy Johnson",
            photoUrl = "https://cdn.vanguardngr.com/wp-content/uploads/2023/07/juk8.jpg",
            bio = "Award-winning Nollywood actress famous for versatile emotional & comedic roles.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_jim",
            name = "Jim Iyke",
            photoUrl = "https://cdn.vanguardngr.com/wp-content/uploads/2024/07/IMG-20240702-WA0007.jpg",
            bio = "Nollywood's quintessential bad boy actor, producer, and entrepreneur.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_rita",
            name = "Rita Dominic",
            photoUrl = "https://cdn.vanguardngr.com/wp-content/uploads/2023/10/jkli.jpg",
            bio = "Africa Movie Academy Award-winning actress and film producer.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_zubby",
            name = "Zubby Michael",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUblkQj67lkwBArhIql4Och1C6MSHnzmXi-FYoTEdSmx6zkwcmIbYmo-I&s=10",
            bio = "High-energy Nollywood action hero and crowd favorite across East Africa.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_regina",
            name = "Regina Daniels",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI9jMVK7T4Km6k1bl2pwAvA2XCeEoRKjEK3oYD6SZcQFI4d0iJFBAFzfJ8&s=10",
            bio = "Popular Nollywood actress and film producer.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_odunlade",
            name = "Odunlade Adekola",
            photoUrl = "https://nollywire.com/wp-content/uploads/2022/12/Odunlade-Odukola-Nollywire-e1677685500708.jpg",
            bio = "Prolific Nollywood actor, singer, and king of hilarious movie memes.",
            country = Country.NIGERIA
        ),
        Actor(
            id = "actor_toyin",
            name = "Toyin Abraham",
            photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3dShr7OfYyCiGla8GzJ5QI3Ec1ShN7ryKF8Yn0nITZSFfIMS0u1QGOCg&s=10",
            bio = "Box-office queen of Nollywood comedy blockbusters.",
            country = Country.NIGERIA
        )
    )

    fun getById(id: String): Actor? = actors.find { it.id == id }
    fun getByIds(ids: List<String>): List<Actor> = actors.filter { ids.contains(it.id) }
}
