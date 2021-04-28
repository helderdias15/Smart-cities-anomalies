package ipvc.cmmv.ei22054.api

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {
    @FormUrlEncoded
    @POST("myslim/utilizador")
    fun VerificarUtilizador(@Field("nome")nome:String?, @Field("pass")pass:String?): Call<List<User>>

    @GET("myslim/ocurrencia/{id}")
    fun getOcurenciasById(@Path("id_utilizador") id_utilizador: Int): Call<List<Ocurrencia>>

    @GET("myslim/ocurrencia")
    fun getAllOcurencias(): Call<List<Ocurrencia>>
}