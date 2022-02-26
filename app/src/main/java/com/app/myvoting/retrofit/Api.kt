package com.annisa.evoteing.retrofit

import com.app.myvoting.retrofit.response.hasil.ModelResultHasilData
import com.app.myvoting.retrofit.response.kandidat.ModelResultData
import com.app.myvoting.retrofit.response.pemilih.ModelResultPemilihData
import com.app.myvoting.retrofit.response.pemilihan.UserRequest
import com.app.myvoting.retrofit.response.pemilihan.UserResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @GET("kandidat")
    fun getKandidat(): Call<ModelResultData>

    @GET("hasil")
    fun getHasil(): Call<ModelResultHasilData>

    @GET("pemilih")
    fun getPemilih(): Call<ModelResultPemilihData>

    @POST("pemilihan")
    fun postPemilihan(
        @Body req: UserRequest?,
    ): Call<UserResponse>
}