package com.example.smartcitiesanomalies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcitiesanomalies.adapter.NotasAdapter
import com.example.smartcitiesanomalies.entidades.Notas
import com.example.smartcitiesanomalies.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NotasActivity : AppCompatActivity(){

 /*   private val newWordActivityRequestCode = 1
    private lateinit var NotasViewModel: NotasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.atividade2_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_notas)
        val adapter = NotasAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        NotasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        NotasViewModel.allNotas.observe(this , Observer{ notas ->
            notas?.let { adapter.setNotas(it)}
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent( this@NotasActivity, AddNote::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AddNote.EXTRA_REPLY)?.let {
                val nota = Notas(titulo = it , descricao = "Descricao")
                NotasViewModel.insert(nota)
            }
        } else {
            Toast.makeText(applicationContext,"titulo vazio", Toast.LENGTH_LONG).show()
        }
    }*/
}