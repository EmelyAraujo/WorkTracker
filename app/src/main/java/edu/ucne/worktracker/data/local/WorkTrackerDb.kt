package edu.ucne.worktracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.worktracker.data.local.dao.MaterialDao
import edu.ucne.worktracker.data.local.dao.ObraDao
import edu.ucne.worktracker.data.local.entity.MaterialEntity
import edu.ucne.worktracker.data.local.entity.ObraEntity

@Database(
    entities = [
        MaterialEntity::class,
        ObraEntity::class
    ],
    version = 5
)
abstract  class WorkTrackerDb: RoomDatabase(){
    abstract val materialDao: MaterialDao
    abstract  val obraDao: ObraDao

}
