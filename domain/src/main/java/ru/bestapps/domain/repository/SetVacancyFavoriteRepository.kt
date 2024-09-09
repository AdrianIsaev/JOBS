package ru.bestapps.domain.repository

interface SetVacancyFavoriteRepository {
    suspend fun setVacancyFavorite(itemId: Int, isFavorite: Boolean)
}