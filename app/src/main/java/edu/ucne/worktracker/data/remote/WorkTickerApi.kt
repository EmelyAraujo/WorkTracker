package edu.ucne.worktracker.data.remote

import edu.ucne.worktracker.data.remote.dto.MaterialDto
import retrofit2.Response
import retrofit2.http.*

interface WorkTickerApi {
    @GET("/api/Materiales")
    suspend fun getMateriales(): List<MaterialDto>

    @POST("/api/Materiales")
    suspend fun postMateriales(@Body materialDto:MaterialDto): MaterialDto

    @PUT("api/Materiales/{id}")
    suspend fun putMateriales(@Path("id") id: Int, @Body materialDto:MaterialDto): Response<Unit>

    @DELETE("api/Materiales{id}")
    suspend fun deleteMateriales(@Path("id")id: Int)

    @GET("/api/Materiales/{id}")
    suspend fun getMaterialesbyId(@Path("id") id: Int): MaterialDto
}