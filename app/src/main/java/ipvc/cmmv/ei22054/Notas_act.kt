package ipvc.cmmv.ei22054

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.cmmv.ei22054.adapter.NotasAdapter
import ipvc.cmmv.ei22054.entidades.Notas
import ipvc.cmmv.ei22054.listeners.RecyclerItemTouchHelper
import ipvc.cmmv.ei22054.listeners.RecyclerTouchListener
import ipvc.cmmv.ei22054.viewModel.NotasViewModel


class Notas_act : AppCompatActivity(), NotasAdapter.NotasAdapterListener {

    private val newWordActivityRequestCode = 1
    private lateinit var notViewModel: NotasViewModel
    var adapter : NotasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        //val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        //root.setFocusable(false)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent( this, AddNote::class.java)
            intent.putExtra("action","create")
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_notas)
        //var adapter = context?.let { NotasAdapter(it) }
        recyclerView?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView?.itemAnimator = DefaultItemAnimator()
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
        recyclerView?.addOnItemTouchListener(
            RecyclerTouchListener(this, recyclerView, ClickListener())
        )
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        })

        recyclerView.layoutManager = LinearLayoutManager(this)

        notViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notViewModel!!.allNotas.observe(this , Observer{ notas ->
            val notesList = notas as MutableList<Notas>
            adapter = this?.let { NotasAdapter(notesList, this, it) }
            recyclerView.adapter = adapter!!

        })

        /*notViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        //val viewModel: NotasViewModel by viewModels()
        notViewModel.allNotas.observe(this, Observer { cities ->
            // Update the cached copy of the words in the adapter.
            *//*cities?.let { adapter.setCities(it) }*//*
        })*/
    }

     override fun onNoteSelected(nota: Notas?) {
        Log.e("nota", nota!!.id.toString())
        val intent = Intent(this, AddNote::class.java);
        intent.putExtra("id",nota.id)
        intent.putExtra("titulo",nota.titulo)
        intent.putExtra("descricao",nota.descricao)
        intent.putExtra("action","view")
        startActivity(intent);
    }

    fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        if (viewHolder is NotasAdapter.MyViewHolder) {
            Log.e("position", position.toString())
            val note = adapter!!.getItem(position)
            Log.e("nota", note!!.id.toString())
            notViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
            notViewModel!!.deleteNota(note.id!!)
            Toast.makeText(this,"Nota apagada", Toast.LENGTH_LONG).show()
        }

    }

    class ClickListener {
        fun onClick() {}
    }
}
