package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.Mascotas
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.database.Propietarios
import com.example.mascotasapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenus() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)


        binding.btnGuardar.setOnClickListener {
            //var esPerro = binding.radioPerro.isChecked
            CoroutineScope(Dispatchers.IO).launch {
                MisMascotasApp.database.propietariosDao().addPropietario(
                    Propietarios(
                        nombre_propietario = binding.nombrePropietario.text.toString(),
                        direccion_propietario = binding.direccionPropietario.text.toString(),
                        tlf_propietario = binding.telefonoPropietario.text.toString()
                    )
                )

                MisMascotasApp.database.mascotasDao().addMascotas(
                    Mascotas(
                        nombre = binding.nombreMascota.text.toString(),
                        esPerro = binding.radioPerro.isChecked,
                        raza = binding.razaMascota.text.toString(),
                        fecha_nacimiento = binding.fechaNacMascota.text.toString(),
                        duenio = binding.nombrePropietario.text.toString()
                    )
                )

                runOnUiThread {
                    clearMascotas()
                }
            }
        }

        binding.btnOtraMascota.setOnClickListener {
            //var esPerro = binding.radioPerro.isChecked

            CoroutineScope(Dispatchers.IO).launch {
                MisMascotasApp.database.mascotasDao().addMascotas(
                    Mascotas(
                        nombre = binding.nombreMascota.text.toString(),
                        esPerro = binding.radioPerro.isChecked,
                        raza = binding.razaMascota.text.toString(),
                        fecha_nacimiento = binding.fechaNacMascota.text.toString(),
                        duenio = binding.nombrePropietario.text.toString()
                    )
                )
            }
        }
    }

    fun clearMascotas() {
        binding.nombreMascota.setText("")
        binding.razaMascota.setText("")
        binding.fechaNacMascota.setText("")
    }
}