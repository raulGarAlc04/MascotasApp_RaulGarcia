package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MascotasDAO {
    @Query("SELECT * FROM mascotas WHERE nombre like :nombre")
    fun obtenerMascotaPorNombre(nombre: String): MutableList<Mascotas>

    @Insert
    fun addMascotas(elemento: Mascotas)

    @Query("SELECT * FROM mascotas WHERE duenio like :duenio")
    fun obtenerMascotasDeDuenio(duenio: String): MutableList<Mascotas>

    @Delete
    fun borrarMascotas(mascota: Mascotas)
}