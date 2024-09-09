package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.GetVacanciesFromLocalDatabaseRepository

class GetVacanciesFromLocalDataUseCase(private val localDatabaseRepository: GetVacanciesFromLocalDatabaseRepository) {
    suspend fun execute(): List<VacancyDomain>{
        return localDatabaseRepository.getAllVacancies()
    }
}