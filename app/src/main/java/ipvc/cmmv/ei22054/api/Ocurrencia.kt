package ipvc.cmmv.ei22054.api

data class Ocurrencia(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val imagem: String,
    val latitude: Double,
    val longitude: Double,
    val id_utilizador: Int
)