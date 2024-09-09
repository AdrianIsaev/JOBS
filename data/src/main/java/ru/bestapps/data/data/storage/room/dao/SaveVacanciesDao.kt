package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.bestapps.data.data.storage.room.entity.Vacancy
@Dao
interface SaveVacanciesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancy(vacancy: Vacancy)

}