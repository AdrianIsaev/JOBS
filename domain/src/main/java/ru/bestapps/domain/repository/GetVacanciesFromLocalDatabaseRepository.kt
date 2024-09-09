package ru.bestapps.domain.repository

import ru.bestapps.domain.models.VacancyDomain

interface GetVacanciesFromLocalDatabaseRepository {
    suspend fun getAllVacancies(): List<VacancyDomain>
}