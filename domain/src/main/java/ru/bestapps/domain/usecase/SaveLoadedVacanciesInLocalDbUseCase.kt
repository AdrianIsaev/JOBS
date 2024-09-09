package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.SaveVacanciesInLocalDbRepository

class SaveLoadedVacanciesInLocalDbUseCase(private val saveVacanciesInLocalDbRepository: SaveVacanciesInLocalDbRepository) {
    suspend fun execute(vacancies: List<VacancyDomain>){
        saveVacanciesInLocalDbRepository.saveVacancies(vacancies)
    }
}