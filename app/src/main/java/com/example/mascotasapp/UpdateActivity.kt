package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpdateActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_update)

        binding.btnActualizar.setOnClickListener {
            val nombrePropietario = binding.nPropietario.text.toString()
            val nuevaDireccion = binding.nuevaDireccion.text.toString()

            updatePropietario(nombrePropietario, nuevaDireccion)
        }
    }

    fun updatePropietario(nombrePropietario: String, nuevaDireccion: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val propietarios = MisMascotasApp.database.propietariosDao().obtenerPropietarioPorNombre(nombrePropietario)

            if (propietarios.isNotEmpty()) {
                val propietario = propietarios[0]
                propietario.direccion_propietario = nuevaDireccion

                MisMascotasApp.database.propietariosDao().updatePropietario(propietario)
            }
        }
    }
}