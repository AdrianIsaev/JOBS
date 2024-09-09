package ru.bestapps.domain.repository

import ru.bestapps.domain.models.VacancyDomain

interface SaveVacanciesInLocalDbRepository {
    suspend fun saveVacancies(vacancies: List<VacancyDomain>)
}