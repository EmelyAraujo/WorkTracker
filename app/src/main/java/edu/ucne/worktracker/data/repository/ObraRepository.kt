package edu.ucne.worktracker.data.repository


import edu.ucne.worktracker.data.local.dao.ObraDao
import edu.ucne.worktracker.data.local.entity.ObraEntity

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObraRepository  @Inject constructor(
    private val obraDao: ObraDao
) {
    suspend fun insert(obra: ObraEntity) {
        return obraDao.insert(obra)
    }
    suspend fun delete(obra: ObraEntity) = obraDao.delete(obra)

    suspend fun find(obraId:Int) = obraDao.find(obraId)

    fun getList(): Flow<List<ObraEntity>> = obraDao.getList()
}