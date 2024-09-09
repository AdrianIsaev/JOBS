package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.room.dao.SetFavoriteDao
import ru.bestapps.domain.repository.SetVacancyFavoriteRepository

class SetVacancyFavoriteRepositoryImpl(private val setFavoriteDao: SetFavoriteDao): SetVacancyFavoriteRepository {
    override suspend fun setVacancyFavorite(itemId: Int, isFavorite: Boolean) {
        setFavoriteDao.setFavorite(itemId= itemId, isFavorite = isFavorite)
    }
}