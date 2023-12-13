package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityMostrarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MostrarActivity : ActivityWithMenus() {
    lateinit var binding: ActivityMostrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_mostrar)

        binding.btnMostrar.setOnClickListener {
            val nombrePropietario = binding.nPropietario.text.toString()

            mostrarPerrosyGatos(nombrePropietario)
        }
    }

    fun mostrarPerrosyGatos(nombrePropietario: String) {
        var contadorPerros: Int = 0
        var contadorGatos: Int = 0

        CoroutineScope(Dispatchers.IO).launch {
            val mascotas = MisMascotasApp.database.mascotasDao().obtenerMascotasDeDuenio(nombrePropietario)

            for (i in mascotas.indices) {
                val mascota = mascotas[i]
                if (mascota.esPerro) {
                    contadorPerros++
                } else {
                    contadorGatos++
                }
            }

            binding.numPerros.text = "NUMERO DE PERROS: " + contadorPerros
            binding.numGatos.text = "NUMERO DE GATOS: " + contadorGatos
        }
    }
}