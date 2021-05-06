package ipvc.cmmv.ei22054

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ipvc.cmmv.ei22054.api.EndPoints
import ipvc.cmmv.ei22054.api.ServiceBuilder
import ipvc.cmmv.ei22054.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.login_p), Context.MODE_PRIVATE
        )

        if (sharedPref != null){
            if(sharedPref.all[getString(R.string.login_shared)]==true){
                var intent = Intent(this, menuLogado::class.java)
                startActivity(intent)
            }
        }


        val useract = findViewById<Button>(R.id.bt_login)
        useract.setOnClickListener {
            val nomeUtilizador = findViewById<EditText>(R.id.et_nome)
            val passUtilizador = findViewById<EditText>(R.id.et_pass)


            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val call = request.VerificarUtilizador(
                nomeUtilizador.text.toString(),
                passUtilizador.text.toString()
            )
            call.enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    //Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
                    val enter = "foi para aqui "
                    Log.e("suc", enter.toString())
                    Toast.makeText(this@Login, R.string.fail_login, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    Log.e("suc", response.toString())
                    if (response.isSuccessful) {
                        val enter = "deu succes"
                        Log.e("suc", enter.toString())
                        val intent = Intent(this@Login , menuLogado::class.java)
                        //val intent = Intent(this, menuLogado::class.java)


                        for(User in response.body()!!) {
                            val sharedPref: SharedPreferences = getSharedPreferences(
                                getString(R.string.login_p), Context.MODE_PRIVATE
                            )
                            with(sharedPref.edit()) {
                                putBoolean(getString(R.string.login_shared), true)
                                putString(getString(R.string.nome), User.nome)
                                putInt(getString(R.string.id), User.id)
                                commit()
                            }
                        }
                        startActivity(intent)
                        Toast.makeText(this@Login, R.string.welcome, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            })
        }
    }
}


