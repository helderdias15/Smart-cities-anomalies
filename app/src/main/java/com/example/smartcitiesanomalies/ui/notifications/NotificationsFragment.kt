package com.example.smartcitiesanomalies.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
//import androidx.lifecycle.ViewModelProviders
import com.example.smartcitiesanomalies.AddNote
import com.example.smartcitiesanomalies.R
import com.example.smartcitiesanomalies.adapter.NotasAdapter
import com.example.smartcitiesanomalies.entidades.Notas
import com.example.smartcitiesanomalies.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.incidentes.listeners.RecyclerItemTouchHelper
import ipvc.estg.incidentes.listeners.RecyclerTouchListener


class NotificationsFragment : Fragment(), NotasAdapter.NotasAdapterListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener  {


    private val newWordActivityRequestCode = 1
    private lateinit var notasViewModel: NotasViewModel
    var adapter : NotasAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        root.setFocusable(false)

        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent( activity, AddNote::class.java)
            intent.putExtra("action","create")
            startActivity(intent)
        }

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview_notas)
        //var adapter = context?.let { NotasAdapter(it) }
        recyclerView?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView?.itemAnimator = DefaultItemAnimator()
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
        recyclerView?.addOnItemTouchListener(
            RecyclerTouchListener(activity, recyclerView, ClickListener())
        )
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        })

        recyclerView.layoutManager = LinearLayoutManager(context)

        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(viewLifecycleOwner , Observer{ notas ->
            val notesList = notas as MutableList<Notas>
            adapter = context?.let { NotasAdapter(notesList, this, it) }
            recyclerView.adapter = adapter!!
        })

        return root

    }

    override fun onNoteSelected(nota: Notas?) {
       Log.e("nota", nota!!.id.toString())
        val intent = Intent(activity, AddNote::class.java);
        intent.putExtra("id",nota.id)
        intent.putExtra("titulo",nota.titulo)
        intent.putExtra("descricao",nota.descricao)
        intent.putExtra("action","view")
        startActivity(intent);
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        if (viewHolder is NotasAdapter.MyViewHolder) {
            Log.e("position", position.toString())
            val note = adapter!!.getItem(position)
            Log.e("nota", note!!.id.toString())

            notasViewModel.deleteNota(note.id!!)
            Toast.makeText(context,"Nota apagada", Toast.LENGTH_LONG).show()
        }

    }

    class ClickListener {
        fun onClick() {}
    }




}