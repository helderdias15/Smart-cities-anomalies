package ipvc.cmmv.ei22054

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import ipvc.cmmv.ei22054.api.EndPoints
import ipvc.cmmv.ei22054.api.Ocurrencia
import ipvc.cmmv.ei22054.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOcurrencia : AppCompatActivity() {

    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var action: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ocurrencia)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val tituloText = findViewById<EditText>(R.id.titulo)
        val descricaoText = findViewById<EditText>(R.id.descricao)
        var id: Int? = 0
        action = intent.getStringExtra("action")


        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.login_p), Context.MODE_PRIVATE
        )

        if (sharedPref != null){
            id = sharedPref.all[getString(R.string.id)] as Int?
        }

        val imagem = "Nulo"
        val userId = id
        if(action == "view"){
            id = intent.getIntExtra("id",0)

            tituloText.setText(intent.getStringExtra("titulo"))
            descricaoText.setText(intent.getStringExtra("descricao"))
        }


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            if(action == "create") {

                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 123
                    )

                } else {

                    if (!tituloText.text.isEmpty()) {

                    fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                        if (location != null) {
                            lastLocation = location

                            val currentLatLng = LatLng(location.latitude, location.longitude)

                            val latitude = lastLocation.latitude
                            val longitude = lastLocation.longitude


                            val request = ServiceBuilder.buildService(EndPoints::class.java)
                            val call = request.InsereOcurrencia(
                                tituloText.text.toString(),
                                descricaoText.text.toString(),
                                imagem.toString(),
                                latitude,
                                longitude,
                                userId
                            )

                            call.enqueue(object : Callback<Ocurrencia> {
                                override fun onResponse(
                                    call: Call<Ocurrencia>?,
                                    response: Response<Ocurrencia>?
                                ) {
                                    Log.e("response", response!!.body().toString())

                                    if (response!!.isSuccessful) {

                                        val intent =
                                            Intent(this@AddOcurrencia, menuLogado::class.java)
                                        startActivity(intent)
                                        Toast.makeText(this@AddOcurrencia, R.string.ocorr_sav, Toast.LENGTH_LONG).show()
                                        finish()
                                    }
                                }

                                override fun onFailure(call: Call<Ocurrencia>?, t: Throwable?) {
                                    Toast.makeText(
                                        applicationContext,
                                        t!!.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.e("error", t!!.message.toString())
                                }
                            })
                        }
                    }
                    }else{
                        Toast.makeText(this,R.string.tit_vazio, Toast.LENGTH_LONG).show()
                    }
                }

            }else if(action == "view") {


                if (!tituloText.text.isEmpty()) {
                    val request = ServiceBuilder.buildService(EndPoints::class.java)
                    val call = request.AtualizaOcorrencia(
                        tituloText.text.toString(),
                        descricaoText.text.toString(),
                        id!!

                    )

                    call.enqueue(object : Callback<Ocurrencia> {
                        override fun onResponse(
                            call: Call<Ocurrencia>?,
                            response: Response<Ocurrencia>?
                        ) {
                            Log.e("response", response!!.body().toString())

                            if (response!!.isSuccessful) {

                                val intent =
                                    Intent(this@AddOcurrencia, menuLogado::class.java)
                                startActivity(intent)
                                Toast.makeText(this@AddOcurrencia, R.string.ocorr_sav, Toast.LENGTH_LONG).show()
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Ocurrencia>?, t: Throwable?) {
                            Toast.makeText(
                                applicationContext,
                                t!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.e("error", t!!.message.toString())

                        }
                    })
                }//
                else{
                    Toast.makeText(this,R.string.tit_vazio, Toast.LENGTH_LONG).show()
                }
            }



        }
    }
}
