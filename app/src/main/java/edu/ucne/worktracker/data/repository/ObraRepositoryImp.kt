package edu.ucne.worktracker.data.repository

import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.remote.ObraApi
import edu.ucne.worktracker.data.remote.dto.ObraDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject



class ObraRepositoryImp @Inject constructor(

    private val api: ObraApi

) : ObraRepositoryApi{

    override fun getObra(): Flow<Resource<List<ObraDto>>> = flow {
        try {
            emit(Resource.Loading())

            val obra =
                api.getObra()

            emit(Resource.Success(obra))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getObraById(id: Int): Flow<Resource<ObraDto>> = flow {
        try {
            emit(Resource.Loading())

            val obra =
                api.getObraById(id)

            emit(Resource.Success(obra))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "HTTP Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "IO Error"))
        }
    }

    override suspend fun putObra(id: Int, obraDto: ObraDto) {
        api.putObra(id, obraDto )
    }

    override suspend fun deleteObra(id: Int, obraDto: ObraDto ) {
        api.deleteObra(id, obraDto)
    }

    override suspend fun postObra(obraDto: ObraDto) {
        api.postObra(obraDto)
    }

}
