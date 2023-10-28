package com.example.botonesproyecto;

import android.Manifest

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var btnLlamar: ImageButton
    private lateinit var btn : Button

    companion object {
        const val PHONE = "666123111"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initEvent()
    }

    private fun initEvent() {
        btn = findViewById(R.id.button)
        btn.setOnClickListener{
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }
        btnLlamar = findViewById(R.id.llamar1)
        btnLlamar.setOnClickListener {
            requestPermissions()
        }
    }

    private fun permissionPhone(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(
                this, "Se necesita habilitar los permisos para llamar",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionPhone()) {
                call()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        } else {
            call()
        }
    }

    private fun call() {
            intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$PHONE")
        }
        startActivity(intent)
    }




}