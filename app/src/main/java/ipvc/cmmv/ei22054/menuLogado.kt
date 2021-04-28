package ipvc.cmmv.ei22054

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class menuLogado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_logado)

        val bt_mapa = findViewById<Button>(R.id.bt_mapa)
        bt_mapa.setOnClickListener{
            val intent = Intent(this, Mapa::class.java)
            startActivity(intent)
        }

    }
}
