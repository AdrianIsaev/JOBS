package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.room.dao.DeleteVacanciesDao
import ru.bestapps.domain.repository.DeleteVacanciesFromLocalDbRepository

class DeleteVacanciesFromLocalDbRepositoryImpl(private val deleteVacanciesDao: DeleteVacanciesDao): DeleteVacanciesFromLocalDbRepository {
    override suspend fun deleteVacancies() {
        deleteVacanciesDao.deleteAllVacancies()
    }
}