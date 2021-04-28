package ipvc.cmmv.ei22054

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_notas = findViewById<Button>(R.id.bt_notas)
        button_notas.setOnClickListener {
            val intent = Intent(this, Notas_act::class.java)
            startActivity(intent)
        }

        val button_login = findViewById<Button>(R.id.bt_login)
        button_login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
