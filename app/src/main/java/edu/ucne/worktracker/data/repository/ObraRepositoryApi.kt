package edu.ucne.worktracker.data.repository

import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.remote.dto.ObraDto
import kotlinx.coroutines.flow.Flow

interface ObraRepositoryApi{

    fun getObra(): Flow<Resource<List<ObraDto>>>
    fun getObraById(id: Int): Flow<Resource<ObraDto>>
    suspend fun putObra(id: Int, obraDto: ObraDto)
    suspend fun deleteObra(id: Int, obraDto: ObraDto)
    suspend fun postObra( obraDto: ObraDto)
}