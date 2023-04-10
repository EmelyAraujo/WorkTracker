package edu.ucne.worktracker.data.remote.dto



data class MaterialDto(
    val materialId: Int? ,
    val obraId: Int? ,
    val fecha: String,
    val descripcion: String,
    val cantidad: Int?,
    val cantRetirada: Int?,
    val suplidor: String,
    val precioUd: Double?
)
