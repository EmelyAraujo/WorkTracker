package edu.ucne.worktracker.data.local.dao

import androidx.room.*
import edu.ucne.worktracker.data.local.entity.ObraEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface ObraDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obraEntity: ObraEntity)

    @Delete
    suspend fun delete(obraEntity: ObraEntity)

    @Query(
        """
        SELECT * 
        FROM Obras
        WHERE ObraId=:obraId
        LIMIT 1
    """
    )
    suspend fun find(obraId: Int): ObraEntity?

    @Query(
        """SELECT * FROM Obras
            ORDER BY obraId desc
        """
    )
    fun getList(): Flow<List<ObraEntity>>


}

class daoe {
    fun save(): Boolean {
        return true
    }
}


