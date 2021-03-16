package com.example.smartcitiesanomalies.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.smartcitiesanomalies.AddNote
import com.example.smartcitiesanomalies.NotasActivity
import com.example.smartcitiesanomalies.R

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

   /* val botao = findViewById<Button>(R.id.ir_atividade2)
    botao.setOnClickListener
    {
        val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root


           /* val intent= Intent(this,NotasActivity::class.java)
            startActivity(intent)*/



    }
}