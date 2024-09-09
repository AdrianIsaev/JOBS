package ru.bestapps.domain.repository


import ru.bestapps.domain.models.VacancyDomain

interface VacancyRepository {
    suspend fun getVacancies(): List<VacancyDomain>
}