package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.VacancyRepository

class GetVacanciesListUseCase(private val vacancyRepositoryImpl: VacancyRepository) {
    suspend fun execute(): List<VacancyDomain>{
        return vacancyRepositoryImpl.getVacancies()
    }
}