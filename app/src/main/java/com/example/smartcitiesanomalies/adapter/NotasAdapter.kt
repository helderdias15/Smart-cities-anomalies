package com.example.smartcitiesanomalies.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcitiesanomalies.R
import com.example.smartcitiesanomalies.entidades.Notas

class NotasAdapter(notesList: MutableList<Notas>, listener: NotasAdapterListener, context: Context) : RecyclerView.Adapter<NotasAdapter.MyViewHolder>() {
    private var notesList: MutableList<Notas>
    private val listener: NotasAdapterListener
    var context : Context = context

    inner class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var viewBackground: RelativeLayout
        var viewForeground: RelativeLayout
        var date: TextView
        var time: TextView

        init {

            title = view.findViewById(R.id.title)
            date = view.findViewById(R.id.date)
            time = view.findViewById(R.id.time)
            viewBackground = view.findViewById(R.id.view_background)
            viewForeground = view.findViewById(R.id.view_foreground)


            view.setOnClickListener { // send selected contact in callback
                listener.onNoteSelected(notesList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_nota, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val nota = notesList[position]
        holder.title.text = nota.titulo
        holder.date.text = nota.titulo
        holder.time.text = nota.titulo
    }


    override fun getItemCount(): Int {
        return notesList.size
    }


    interface NotasAdapterListener {
        fun onNoteSelected(nota: Notas?)
    }

    fun getItem(position: Int): Notas {
        return notesList[position]
    }

    fun getItemById(id: Int): Notas {
        for (i in 0 until notesList.size) {
            if(notesList[i].id == id){
                return notesList[i];
            }
        }
        return notesList[0]
    }

    fun removeItem(position: Int) {
        notesList.removeAt(position)
        notifyItemRemoved(position)
    }

    init {

        setHasStableIds(true)
        this.listener = listener
        this.notesList = notesList!!
    }
}