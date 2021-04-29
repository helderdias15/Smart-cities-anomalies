package ipvc.cmmv.ei22054.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.cmmv.ei22054.R
import ipvc.cmmv.ei22054.api.Ocurrencia
import ipvc.cmmv.ei22054.api.User

class OcurrenciasAdapter(val ocurrencia: List<Ocurrencia>): RecyclerView.Adapter<OcurrenciaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OcurrenciaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_ocurrencias , parent ,false)
        return OcurrenciaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ocurrencia.size
    }

    override fun onBindViewHolder(holder: OcurrenciaViewHolder, position: Int) {
        return holder.bind(ocurrencia[position])
    }
}

class OcurrenciaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val titulo: TextView = itemView.findViewById(R.id.o_titulo)
    private val descricao: TextView = itemView.findViewById(R.id.o_descricao)



    fun bind(ocurrencia: Ocurrencia){
        titulo.text = ocurrencia.titulo
        descricao.text = ocurrencia.descricao
    }
}