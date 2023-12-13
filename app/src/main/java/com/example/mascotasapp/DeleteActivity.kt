package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeleteActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_delete)

        binding.btonEliminar.setOnClickListener {
            val nombrePropietario = binding.escribePropietario.text.toString()

            borrarMascotas(nombrePropietario)
        }
    }

    fun borrarMascotas(duenio: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val mascotas_borrar = MisMascotasApp.database.mascotasDao().obtenerMascotasDeDuenio(duenio)

            for (i in mascotas_borrar.indices) {
                val mascota = mascotas_borrar[i]
                MisMascotasApp.database.mascotasDao().borrarMascotas(mascota)
            }
        }
    }
}