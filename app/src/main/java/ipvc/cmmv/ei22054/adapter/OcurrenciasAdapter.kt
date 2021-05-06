package ipvc.cmmv.ei22054.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.cmmv.ei22054.R
import ipvc.cmmv.ei22054.api.Ocurrencia
import ipvc.cmmv.ei22054.api.User
import ipvc.cmmv.ei22054.menuLogado


class OcurrenciasAdapter(ocurrenciaList: MutableList<Ocurrencia>, listener: menuLogado, context: Context) : RecyclerView.Adapter<OcurrenciasAdapter.MyViewHolder>() {
    private var ocurrenciaList: MutableList<Ocurrencia>
    private val listener: OcurenciaAdapterListener
    var context : Context = context

    inner class MyViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var titulo: TextView
        var viewBackground: RelativeLayout
        var viewForeground: RelativeLayout
        var descricao: TextView


        init {

            titulo = view.findViewById(R.id.o_titulo)
            descricao = view.findViewById(R.id.o_descricao)
            viewBackground = view.findViewById(R.id.view_background)
            viewForeground = view.findViewById(R.id.view_foreground)


            view.setOnClickListener { // send selected contact in callback
                listener.onOcurenciaSelected(ocurrenciaList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_ocurrencias, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val ocurencia = ocurrenciaList[position]
        holder.titulo.text = ocurencia.titulo
        holder.descricao.text = ocurencia.descricao

    }


    override fun getItemCount(): Int {
        return ocurrenciaList.size
    }


    interface OcurenciaAdapterListener {
        fun onOcurenciaSelected(ocurencia: Ocurrencia?)
    }

    fun getItem(position: Int): Ocurrencia {
        return ocurrenciaList[position]
    }

    fun getItemById(id: Int): Ocurrencia {
        for (i in 0 until ocurrenciaList.size) {
            if(ocurrenciaList[i].id == id){
                return ocurrenciaList[i];
            }
        }
        return ocurrenciaList[0]
    }

    fun removeItem(position: Int) {
        ocurrenciaList.removeAt(position)
        notifyItemRemoved(position)
    }

    init {

        setHasStableIds(true)
        this.listener = listener
        this.ocurrenciaList = ocurrenciaList!!
    }
}