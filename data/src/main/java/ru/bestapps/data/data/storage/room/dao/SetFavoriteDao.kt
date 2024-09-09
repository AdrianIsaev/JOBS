package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface SetFavoriteDao {
    @Query("UPDATE vacancies SET isFavorite = :isFavorite WHERE ids = :itemId")
    suspend fun setFavorite(itemId: Int, isFavorite: Boolean)
}