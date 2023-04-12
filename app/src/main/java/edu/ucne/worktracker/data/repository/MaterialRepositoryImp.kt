package edu.ucne.worktracker.data.repository

import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.remote.MaterialApi
import edu.ucne.worktracker.data.remote.dto.MaterialDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MaterialRepositoryImp @Inject constructor(

    private val api: MaterialApi

) : MaterialRepositoryApi{

    override fun getMaterial(): Flow<Resource<List<MaterialDto>>> = flow {
        try {
            emit(Resource.Loading())

            val material =
                api.getMaterial()

            emit(Resource.Success(material))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getMaterialById(id: Int): Flow<Resource<MaterialDto>> = flow {
        try {
            emit(Resource.Loading())//Indicamos que estamos cargando

            val material =
                api.getMaterialById(id)//Descarga el ticket deseado, que se supone que tardará algo de tiempo

            emit(Resource.Success(material))//Indicamos que esta listo

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "IO Error"))
        }
    }

    override suspend fun putMaterial(id: Int, materialDto: MaterialDto) {
        api.putMaterial(id, materialDto)
    }

    override suspend fun deleteMaterial(id: Int, materialDto: MaterialDto) {
        api.deleteMaterial(id, materialDto)
    }

    override suspend fun postMaterial(materialDto: MaterialDto) {
        api.postMaterial(materialDto)
    }

}
