package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
@Dao
interface DeleteVacanciesDao {
    @Query("DELETE FROM vacancies")
    suspend fun deleteAllVacancies()
}