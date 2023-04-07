package edu.ucne.worktracker.data.repository

import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.remote.dto.MaterialDto
import kotlinx.coroutines.flow.Flow

interface MaterialRepository{


    fun getMaterial(): Flow<Resource<List<MaterialDto>>>

    fun getMaterialById(id: Int): Flow<Resource<MaterialDto>>

    suspend fun putMaterial(id: Int, materialDto: MaterialDto)

    suspend fun deleteMaterial(id: Int)
}