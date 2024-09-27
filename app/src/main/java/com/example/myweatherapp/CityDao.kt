package com.example.myweatherapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM city ORDER BY id DESC LIMIT 1")
    suspend fun getLastCity(): City?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)
}
