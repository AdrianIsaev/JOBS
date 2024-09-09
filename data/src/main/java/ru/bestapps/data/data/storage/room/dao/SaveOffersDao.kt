package ru.bestapps.data.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.bestapps.data.data.storage.room.entity.Offer

@Dao
interface SaveOffersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOffer(offer: Offer)
}