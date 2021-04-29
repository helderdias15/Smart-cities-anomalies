package ipvc.cmmv.ei22054

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.cmmv.ei22054.adapter.OcurrenciasAdapter
import ipvc.cmmv.ei22054.api.EndPoints
import ipvc.cmmv.ei22054.api.Ocurrencia
import ipvc.cmmv.ei22054.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_menu_logado.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class menuLogado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_logado)

        val add_ocurrencia = findViewById<FloatingActionButton>(R.id.fab_ocurrencia)
        add_ocurrencia.setOnClickListener{
            val intent = Intent(this, AddOcurrencia::class.java)
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
                        adapter = OcurrenciasAdapter(response.body()!!)

                    }
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
    }
}
