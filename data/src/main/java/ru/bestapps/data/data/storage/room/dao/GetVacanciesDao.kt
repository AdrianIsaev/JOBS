package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.bestapps.data.data.storage.room.entity.Vacancy

@Dao
interface GetVacanciesDao {
    @Query("SELECT * FROM vacancies")
    suspend fun getAllVacancies(): List<Vacancy>
}