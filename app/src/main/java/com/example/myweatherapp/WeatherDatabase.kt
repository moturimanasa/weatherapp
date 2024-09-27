package com.example.myweatherapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
