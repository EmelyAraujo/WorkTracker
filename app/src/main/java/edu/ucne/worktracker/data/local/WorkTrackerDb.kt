package edu.ucne.worktracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.worktracker.data.local.dao.MaterialDao
import edu.ucne.worktracker.data.local.entity.MaterialEntity

@Database(
    entities = [
        MaterialEntity::class,
    ],
    version = 1
)
abstract  class WorkTrackerDb: RoomDatabase(){
    abstract val materialDao: MaterialDao

}
