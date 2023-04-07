package edu.ucne.worktracker.data.repository

import edu.ucne.worktracker.data.local.dao.MaterialDao
import edu.ucne.worktracker.data.local.entity.MaterialEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MaterialRepositoryLocal @Inject constructor(
    private val materialDao: MaterialDao
) {
    suspend fun insert(material: MaterialEntity) {
        return materialDao.insert(material)
    }
    suspend fun delete(material: MaterialEntity) = materialDao.delete(material)

    suspend fun find(materialId:Int) = materialDao.find(materialId)

    fun getList(): Flow<List<MaterialEntity>> = materialDao.getList()
}