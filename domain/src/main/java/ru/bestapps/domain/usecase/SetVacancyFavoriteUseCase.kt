package ru.bestapps.domain.usecase

import ru.bestapps.domain.repository.SetVacancyFavoriteRepository

class SetVacancyFavoriteUseCase(private val setVacancyFavoriteRepository: SetVacancyFavoriteRepository) {
    suspend fun execute(id: Int, isF: Boolean){
        setVacancyFavoriteRepository.setVacancyFavorite(id, isF)
    }
}