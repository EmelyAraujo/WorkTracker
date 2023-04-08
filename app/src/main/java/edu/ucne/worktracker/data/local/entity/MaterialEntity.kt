package edu.ucne.worktracker.data.local.entity

import android.widget.DatePicker
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Materiales")
class MaterialEntity(
    @PrimaryKey(autoGenerate = true)
    val materialId: Int? =null,
    val obraId: Int? =null,
    val fecha: String,
    val descripcion: String,
    val cantidad: Int?,
    val cantRetirada: Int?,
    val suplidor: String,
    val precioUd: Double?
)