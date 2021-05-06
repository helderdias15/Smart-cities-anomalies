package ipvc.cmmv.ei22054

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ipvc.cmmv.ei22054.entidades.Notas
import ipvc.cmmv.ei22054.viewModel.NotasViewModel

class AddNote : AppCompatActivity() {

    private lateinit var tituloText: EditText
    private lateinit var descricaoText: EditText
    private lateinit var notasViewModel: NotasViewModel
    var action: String? = null
    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        action = intent.getStringExtra("action")



        tituloText = findViewById(R.id.titulo)
        descricaoText = findViewById(R.id.descricao)

        if(action == "view"){
            id = intent.getIntExtra("id",0)

            tituloText.setText(intent.getStringExtra("titulo"))
            descricaoText.setText(intent.getStringExtra("descricao"))
        }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            /*val replyIntent = Intent()
            if (TextUtils.isEmpty(tituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                replyIntent.putExtra(EXTRA_REPLY_TITULO, tituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESCRICAO, descricaoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }*/
            if(action == "create"){
                if (!tituloText.text.isEmpty()){
                    notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
                    val nota = Notas(titulo = tituloText.text.toString() , descricao = descricaoText.text.toString())
                    Log.e("nota",  nota.toString())
                    notasViewModel.insert(nota)
                    finish()
                    Toast.makeText(this, R.string.nt_sav, Toast.LENGTH_LONG).show()
                }else {
                    tituloText.error
                    Toast.makeText(this,R.string.tit_vazio, Toast.LENGTH_LONG).show()
                }
            }else if(action == "view"){
                if (!tituloText.text.isEmpty()){
                    Log.e("id", id.toString())
                    val nota = Notas(id=id,titulo = tituloText.text.toString() , descricao = descricaoText.text.toString())
                    notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
                    notasViewModel.updateNota(nota)
                    finish()
                    Toast.makeText(this, R.string.nt_sav, Toast.LENGTH_LONG).show()
                }else{
                    tituloText.error
                    Toast.makeText(this, R.string.tit_vazio, Toast.LENGTH_LONG).show()
                }

            }


        }
    }
    companion object{
        const val EXTRA_REPLY_TITULO = "com.example.android.titulo"
        //const val EXTRA_REPLY_DESCRICAO = "com.example.android.descricao"
    }
}
