package edu.ucne.worktracker.data.remote

import edu.ucne.worktracker.data.remote.dto.MaterialDto
import retrofit2.Response
import retrofit2.http.*

interface MaterialApi{

    @GET("/api/Material")
    suspend fun getMaterial():List<MaterialDto>

    @GET("/api/Material/{id}")
    suspend fun getMaterialById(@Path("id") id:Int) : MaterialDto

    @PUT("/api/Material/{id}")
    suspend fun putMaterial(@Path("id") id: Int, @Body TicketDto : MaterialDto) : Response<Unit>

    @POST("/api/Material")
    suspend fun postMaterial(@Body materialDto: MaterialDto): MaterialDto

    @DELETE("/api/Material/{id}")
    suspend fun deleteMaterial(@Path("id") id: Int,@Body materialDto: MaterialDto )  : MaterialDto
}