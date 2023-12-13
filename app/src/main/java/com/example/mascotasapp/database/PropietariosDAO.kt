package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PropietariosDAO {
    @Query("SELECT * FROM propietarios WHERE nombre_propietario like :nombre")
    fun obtenerPropietarioPorNombre(nombre: String): MutableList<Propietarios>

    @Insert
    fun addPropietario(elemento: Propietarios)

    @Update
    fun updatePropietario(propietario: Propietarios)

    @Transaction
    @Query("SELECT * FROM propietarios WHERE nombre_propietario like :nombre_propietario")
    fun mascotasDeUnPropietario(nombre_propietario: String): PropietarioConMascotas
}