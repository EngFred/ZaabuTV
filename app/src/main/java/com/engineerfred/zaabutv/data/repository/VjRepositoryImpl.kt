package com.engineerfred.zaabutv.data.repository

import com.engineerfred.zaabutv.data.mockdata.MockVjs
import com.engineerfred.zaabutv.domain.model.Vj
import com.engineerfred.zaabutv.domain.repository.VjRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VjRepositoryImpl @Inject constructor() : VjRepository {

    override fun getVjs(): Flow<List<Vj>> = flow {
        delay(200)
        emit(MockVjs.vjs)
    }

    override fun getVjById(id: String): Flow<Vj?> = flow {
        delay(200)
        emit(MockVjs.getById(id))
    }
}
