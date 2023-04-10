package edu.ucne.worktracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.ucne.worktracker.data.remote.dto.MaterialDto
import edu.ucne.worktracker.data.remote.dto.ObraDto

@Entity(tableName = "Obras")
class ObraEntity (
    @PrimaryKey(autoGenerate = true)
    val obraId: Int? = null ,
    val duenoObra: String
)

fun ObraEntity.toMaterialDto(): ObraDto {
    return ObraDto(
        obraId = this.obraId ?:0,
        duenoObra = this.duenoObra
    )
}
