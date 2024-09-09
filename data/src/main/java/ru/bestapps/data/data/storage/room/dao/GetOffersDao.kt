package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.bestapps.data.data.storage.room.entity.Offer

@Dao
interface GetOffersDao {
    @Query("SELECT * FROM offers")
    suspend fun getOffers(): List<Offer>
}