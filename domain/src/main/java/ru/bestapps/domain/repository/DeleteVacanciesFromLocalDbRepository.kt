package ru.bestapps.domain.repository

interface DeleteVacanciesFromLocalDbRepository {
    suspend fun deleteVacancies()
}