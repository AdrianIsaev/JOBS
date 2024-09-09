package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.bestapps.data.data.storage.room.entity.Vacancy

@Dao
interface GetVacancyDao {
    @Query("SELECT * FROM vacancies WHERE ids = :vacancyId")
    suspend fun getVacancyById(vacancyId: Int): Vacancy
}