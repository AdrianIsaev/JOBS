package ru.bestapps.domain.repository

import ru.bestapps.domain.models.VacancyDomain

interface GetCurrentVacancyRepository {
    suspend fun getCurrentVacancy(id:Int):VacancyDomain
}