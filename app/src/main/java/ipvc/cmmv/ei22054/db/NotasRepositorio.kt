package ipvc.cmmv.ei22054.db

import androidx.lifecycle.LiveData
import ipvc.cmmv.ei22054.dao.NotasDao
import ipvc.cmmv.ei22054.entidades.Notas

class NotasRepositorio(private val notasDao: NotasDao){
    val allNotas: LiveData<List<Notas>> = notasDao.getAlphabetizedNotes()

    suspend fun insert(notas: Notas){
        notasDao.insert(notas)
    }

    suspend fun deleteNotas(id: Int){
        notasDao.deleteNota(id)
    }

    suspend fun updateNota ( notas: Notas){
        notasDao.updateNota(notas)
    }

}