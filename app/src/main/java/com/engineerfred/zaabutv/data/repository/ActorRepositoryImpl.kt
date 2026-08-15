package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockActors
import com.engineerfred.zaabutv.domain.model.Actor
import com.engineerfred.zaabutv.domain.repository.ActorRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorRepositoryImpl @Inject constructor() : ActorRepository {

    override fun getActorById(id: String): Flow<Actor?> = flow {
        delay(150)
        emit(MockActors.getById(id))
    }

    override fun getActorsByIds(ids: List<String>): Flow<List<Actor>> = flow {
        delay(150)
        emit(MockActors.getByIds(ids))
    }
}
