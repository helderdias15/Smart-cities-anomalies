package com.example.smartcitiesanomalies.ui.notifications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.lifecycle.ViewModelProviders
import com.example.smartcitiesanomalies.AddNote
import com.example.smartcitiesanomalies.NotasActivity
import com.example.smartcitiesanomalies.R
import com.example.smartcitiesanomalies.adapter.NotasAdapter
import com.example.smartcitiesanomalies.entidades.Notas
import com.example.smartcitiesanomalies.teste
import com.example.smartcitiesanomalies.ui.home.HomeViewModel
import com.example.smartcitiesanomalies.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton




class NotificationsFragment : Fragment() {

    private val newWordActivityRequestCode = 1
    private lateinit var notasViewModel: NotasViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
       // notificationsViewModel =
        //    ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
      //  notificationsViewModel.text.observe(this, Observer {
       //     textView.text = it
       // })

      /*  {
            homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
            val root = inflater.inflate(R.layout.fragment_home, container, false)
            val textView: TextView = root.findViewById(R.id.text_home)
            homeViewModel.text.observe(this, Observer {
                textView.text = it
            })
            return root
        }
    }*/

        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent( activity, AddNote::class.java)
            startActivity(intent)
        }

       val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview_notas)
        val adapter = context?.let { NotasAdapter(it) }
        recyclerView.adapter = adapter!!
        recyclerView.layoutManager = LinearLayoutManager(context)

        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this , Observer{ notas ->
            Log.e("notas" , notas.toString())
            notas?.let { adapter.setNotas(it)}
        })





        //root.findViewById<Button>(R.id.fab).setOnClickListener {

          //  val intent = Intent(activity, teste::class.java)
          //  startActivity(intent)

        //}

            /*


            private val newWordActivityRequestCode = 1
            lateinit var NotasViewModel: NotasViewModel
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


        return root
        /*notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(com.example.smartcitiesanomalies.R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(com.example.smartcitiesanomalies.R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root



        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<Button>(com.example.smartcitiesanomalies.R.id.ir_atividade2).setOnClickListener{
                //FirebaseAuth.getInstance().signOut()

                val intent = Intent (getActivity(), NotasActivity::class.java)
                getActivity()
                startActivity(intent)
            }
        }
        */


    }
    

}