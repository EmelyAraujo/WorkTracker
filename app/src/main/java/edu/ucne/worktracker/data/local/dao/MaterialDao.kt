package edu.ucne.worktracker.data.local.dao

import androidx.room.*
import edu.ucne.worktracker.data.local.entity.MaterialEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MaterialDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(materialEntity: MaterialEntity)

    @Delete
    suspend fun delete(materialEntity: MaterialEntity)

    @Query(
        """
        SELECT * 
        FROM Materiales
        WHERE MaterialId=:materialId
        LIMIT 1
    """
    )
    suspend fun find(materialId: Int): MaterialEntity?

    @Query(
        """SELECT * FROM Materiales
            ORDER BY materialId desc
        """
    )
    fun getList(): Flow<List<MaterialEntity>>

}

class dao {
    fun save(): Boolean {
        return true
    }
}



