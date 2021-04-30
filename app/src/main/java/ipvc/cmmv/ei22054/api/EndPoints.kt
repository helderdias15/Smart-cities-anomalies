package ipvc.cmmv.ei22054.api

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {
    @FormUrlEncoded
    @POST("myslim/utilizador")
    fun VerificarUtilizador(@Field("nome")nome:String?, @Field("pass")pass:String?): Call<List<User>>

    @GET("myslim/ocurrencia/{id}")
    fun getOcurenciasById(@Path("id") id: Int): Call<List<Ocurrencia>>

    @GET("myslim/ocurrencia")
    fun getAllOcurencias(): Call<List<Ocurrencia>>

    @FormUrlEncoded
    @POST("myslim/ocurrencia_insert")
    fun InsereOcurrencia(@Field("titulo")titulo:String?, @Field("descricao")descricao:String?, @Field("imagem")imagem:String? , @Field("latitude")latitude:Double? , @Field("longitude")longitude:Double?, @Field("id_utilizador")id_utilizador:Int? ): Call<Ocurrencia>

}