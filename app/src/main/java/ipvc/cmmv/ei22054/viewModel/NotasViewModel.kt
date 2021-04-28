package ipvc.cmmv.ei22054.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.cmmv.ei22054.db.NotasDB
import ipvc.cmmv.ei22054.db.NotasRepositorio
import ipvc.cmmv.ei22054.entidades.Notas


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotasViewModel (application: Application): AndroidViewModel(application){
    private val repositorio: NotasRepositorio
    val allNotas: LiveData<List<Notas>>

    init {
        val notasDao = NotasDB.getDatabase(application, viewModelScope).notasDao()
        repositorio = NotasRepositorio(notasDao)
        allNotas = repositorio.allNotas
    }

    fun insert (notas: Notas) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.insert(notas)
    }

    fun deleteNota (id : Int ) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.deleteNotas(id)
    }

    fun updateNota(notas: Notas) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.updateNota(notas)
    }
}