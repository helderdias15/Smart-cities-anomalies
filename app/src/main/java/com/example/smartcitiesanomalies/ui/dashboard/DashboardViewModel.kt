package com.example.smartcitiesanomalies.ui.dashboard

import android.app.PendingIntent.getActivity
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartcitiesanomalies.R

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
      //  value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


}