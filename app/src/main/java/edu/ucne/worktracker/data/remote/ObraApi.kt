package edu.ucne.worktracker.data.remote


import edu.ucne.worktracker.data.remote.dto.ObraDto
import retrofit2.Response
import retrofit2.http.*

interface ObraApi{

    @GET("/api/Obra")
    suspend fun getObra():List<ObraDto>

    @GET("/api/Obra/{id}")
    suspend fun getObraById(@Path("id") id:Int) : ObraDto

    @PUT("/api/Obra/{id}")
    suspend fun putObra(@Path("id") id: Int, @Body obraDto: ObraDto ) : Response<Unit>

    @POST("/api/Obra")
    suspend fun postObra(@Body obraDto: ObraDto): ObraDto

    @DELETE("/api/Obra/{id}")
    suspend fun deleteObra(@Path("id") id: Int, @Body obraDto: ObraDto)  : ObraDto

}