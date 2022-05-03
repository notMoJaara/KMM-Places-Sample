package com.mohamad.kmmplaces.localStorage.dao

import com.mohamad.kmmplaces.localStorage.PoiEntity
import com.mohamad.kmmplaces.localStorage.PoiEntityQueries
import com.mohamad.kmmplaces.util.KmmDispatcher
import com.mohamad.kmmplaces.util.KmmDispatcherImpl
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface PoiDAO {
    suspend fun selectAll(): List<PoiEntity>
    suspend fun insert(id: Long, name: String, location: String)
    suspend fun selectAllFlow(): Flow<List<PoiEntity>>
    suspend fun deleteAll()
}

class PoiDAOImpl(
    private val queries: PoiEntityQueries,
    private val dispatcher: KmmDispatcher = KmmDispatcherImpl
) : PoiDAO {
    override suspend fun selectAll() = withContext(dispatcher.io) { queries.selectAll().executeAsList() }
    override suspend fun insert(id: Long, name: String, location: String) =
        withContext(dispatcher.io) { queries.insertOrUpdate(id = id, name = name, location = location) }

    override suspend fun selectAllFlow() = withContext(dispatcher.io) { queries.selectAll().asFlow().mapToList() }
    override suspend fun deleteAll() = withContext(dispatcher.io) { queries.deleteAll() }
}