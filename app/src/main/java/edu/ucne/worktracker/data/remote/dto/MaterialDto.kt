package edu.ucne.worktracker.data.remote.dto

import android.widget.DatePicker

data class MaterialDto(
    val materialId: Int,
    val duenoObra: String,
    val fecha: String,
    val descripcion: String,
    val catidad: Int,
    val cantRetirada: Int,
    val suplidor: String,
    val precioUd: Double
)
