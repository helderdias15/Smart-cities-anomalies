package ipvc.cmmv.ei22054

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.cmmv.ei22054.adapter.OcurrenciasAdapter
import ipvc.cmmv.ei22054.api.EndPoints
import ipvc.cmmv.ei22054.api.Ocurrencia
import ipvc.cmmv.ei22054.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_menu_logado.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.ItemTouchHelper
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService



class menuLogado : AppCompatActivity(), OcurrenciasAdapter.OcurenciaAdapterListener {

    var mAdapter : OcurrenciasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_logado)

        val add_ocurrencia = findViewById<FloatingActionButton>(R.id.fab_ocurrencia)
        add_ocurrencia.setOnClickListener{
            val intent = Intent(this, AddOcurrencia::class.java)
            intent.putExtra("action","create")
            startActivity(intent)
        }

        val bt_mapa = findViewById<Button>(R.id.bt_mapa)
        bt_mapa.setOnClickListener{
            val intent = Intent(this, Mapa::class.java)
            startActivity(intent)
        }
        var id: Int? = 0
        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.login_p), Context.MODE_PRIVATE
        )

        if (sharedPref != null) {
            id = sharedPref.all[getString(R.string.id)] as Int?
        }

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getOcurenciasById( id.toString().toInt() )
        Log.d("numero_id" , id.toString())
        Log.d("teste" , call.toString())

        call.enqueue(object : Callback<List<Ocurrencia>> {
            override fun onResponse(call: Call<List<Ocurrencia>>, response: Response<List<Ocurrencia>>) {
                if (response.isSuccessful){
                    recyclerview_ocurrencias.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@menuLogado)
                        adapter = OcurrenciasAdapter((response.body() as MutableList<Ocurrencia>?)!!,this@menuLogado,this@menuLogado)
                        mAdapter = adapter as OcurrenciasAdapter
                    }

                    val touchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                            private val background = ColorDrawable(Color.BLACK)

                            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                                return false
                            }

                            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                                Toast.makeText(this@menuLogado, R.string.ocorr_del, Toast.LENGTH_SHORT).show()

                                val item = mAdapter!!.getItem(viewHolder.adapterPosition)
                                val request = ServiceBuilder.buildService(EndPoints::class.java)
                                val call = request.EliminaOcorrencia(item.id)

                                call.enqueue(object : Callback<Ocurrencia> {
                                    override fun onResponse(call: Call<Ocurrencia>, response: Response<Ocurrencia>) {
                                        mAdapter!!.removeItem(viewHolder.adapterPosition)

                                    }
                                    override fun onFailure(call: Call<Ocurrencia>, t: Throwable) {
                                        mAdapter!!.notifyDataSetChanged()
                                    }
                                })



                            }

                    }
                    val itemTouchHelper = ItemTouchHelper(touchHelperCallback)
                    itemTouchHelper.attachToRecyclerView(recyclerview_ocurrencias)
                }
            }

            override fun onFailure(call: Call<List<Ocurrencia>>,t:Throwable){
                Toast.makeText(this@menuLogado, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun logout(view: View){
        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.login_p), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.login_shared), false)
            putString(getString(R.string.nome), "")
            putInt(getString(R.string.id), 0)
            commit()
        }

        val intent = Intent(this, Login::class.java)

        startActivity(intent)
        finish()
    }



    override fun onOcurenciaSelected(ocurrencia: Ocurrencia?) {
        Log.e("nota", ocurrencia!!.id.toString())
        val intent = Intent(this, AddOcurrencia::class.java);
        intent.putExtra("id",ocurrencia.id)
        intent.putExtra("titulo",ocurrencia.titulo)
        intent.putExtra("descricao",ocurrencia.descricao)
        intent.putExtra("action","view")
        startActivity(intent);
    }
}

