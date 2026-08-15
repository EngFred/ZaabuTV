package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.Actor
import kotlinx.coroutines.flow.Flow

interface ActorRepository {
    fun getActorById(id: String): Flow<Actor?>
    fun getActorsByIds(ids: List<String>): Flow<List<Actor>>
}
