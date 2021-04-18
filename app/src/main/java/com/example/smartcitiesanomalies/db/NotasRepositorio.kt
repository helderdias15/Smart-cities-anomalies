package com.example.smartcitiesanomalies.db

import androidx.lifecycle.LiveData
import com.example.smartcitiesanomalies.dao.NotasDao
import com.example.smartcitiesanomalies.entidades.Notas

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