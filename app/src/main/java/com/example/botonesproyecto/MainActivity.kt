package com.example.botonesproyecto


import android.content.Intent
import android.net.Uri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.Toast
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var btnLlamar: ImageButton
    private lateinit var btnBuscar: ImageButton
    private lateinit var btnAlarma : ImageButton
    private lateinit var btnCorreo : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()
    }



    private fun initEvent() {
        btnLlamar = findViewById(R.id.llamar)
        btnLlamar.setOnClickListener{
            view->
            intent = Intent(this, SecondActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }
        var url = "https://www.google.com/?hl=es"
        btnBuscar = findViewById(R.id.link)
        btnBuscar.setOnClickListener{
            view->
            var link = Uri.parse(url)
            intent =Intent(Intent.ACTION_VIEW,link)
            startActivity(intent)

        }
        var url1="https://www.google.com/intl/es/gmail/about/"
        btnCorreo = findViewById(R.id.correo);
        btnCorreo.setOnClickListener{
            view->
            var link = Uri.parse(url1)//Uri identificar recursos por internet
            intent = Intent(Intent.ACTION_VIEW,link)//para ver su contenido
            startActivity(intent)
        }

        btnAlarma = findViewById(R.id.alarma)
        btnAlarma.setOnClickListener {
            crearAlarma()
        }


    }


    private fun crearAlarma() {
        val alarma = Calendar.getInstance()
        alarma.add(Calendar.MINUTE, 2)

            intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma en 2 minutos")
            putExtra(AlarmClock.EXTRA_HOUR, alarma.get(Calendar.HOUR_OF_DAY))
            putExtra(AlarmClock.EXTRA_MINUTES, alarma.get(Calendar.MINUTE))
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No se puede crear la alarma", Toast.LENGTH_SHORT).show()
        }
    }


}


