package edu.ucne.worktracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Obras")
class ObraEntity (
    @PrimaryKey(autoGenerate = true)
    val obraId: Int? = null ,
    val duenoObra: String
)

