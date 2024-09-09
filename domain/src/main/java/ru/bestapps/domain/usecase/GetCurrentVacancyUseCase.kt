package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.GetCurrentVacancyRepository

class GetCurrentVacancyUseCase(private val getCurrentVacancyRepository: GetCurrentVacancyRepository) {
    suspend fun execute(id: Int): VacancyDomain{
        return getCurrentVacancyRepository.getCurrentVacancy(id)
    }
}