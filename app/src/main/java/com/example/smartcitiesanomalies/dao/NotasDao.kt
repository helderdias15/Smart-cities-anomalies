package com.example.smartcitiesanomalies.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.smartcitiesanomalies.entidades.Notas
@Dao
interface NotasDao {

    @Query("Select * from tabela_notas order by titulo ASC")
    fun getAlphabetizedNotes(): LiveData<List<Notas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notas: Notas)

    @Query("Delete from tabela_notas")
    suspend fun deleteAll()

    @Query("DELETE FROM tabela_notas where id == :id")
    suspend fun deleteNota(id: Int)

    @Update
    suspend fun updateNota(notas: Notas)
}