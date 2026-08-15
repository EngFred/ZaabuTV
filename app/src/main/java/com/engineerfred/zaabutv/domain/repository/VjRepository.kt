package com.engineerfred.zaabutv.domain.repository

import com.engineerfred.zaabutv.domain.model.Vj
import kotlinx.coroutines.flow.Flow

interface VjRepository {
    fun getVjs(): Flow<List<Vj>>
    fun getVjById(id: String): Flow<Vj?>
}
