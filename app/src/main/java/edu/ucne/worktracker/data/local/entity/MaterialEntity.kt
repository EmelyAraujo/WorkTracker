package edu.ucne.worktracker.data.local.entity

import android.widget.DatePicker
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.ucne.worktracker.data.remote.dto.MaterialDto

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
fun MaterialEntity.toMaterialDto(): MaterialDto {
    return MaterialDto(
      materialId= this.materialId ?:0,
        obraId = this.obraId ?:0,
        fecha= this.fecha,
        descripcion= this.descripcion,
        cantidad = this.cantidad,
        cantRetirada= this.cantRetirada,
        suplidor= this.suplidor,
        precioUd = this.precioUd
    )
}