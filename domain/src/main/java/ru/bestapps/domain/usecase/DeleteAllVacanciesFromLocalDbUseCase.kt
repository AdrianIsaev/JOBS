package ru.bestapps.domain.usecase

import ru.bestapps.domain.repository.DeleteVacanciesFromLocalDbRepository

class DeleteAllVacanciesFromLocalDbUseCase(private val deleteVacanciesFromLocalDbRepository: DeleteVacanciesFromLocalDbRepository) {
    suspend fun execute(){
        deleteVacanciesFromLocalDbRepository.deleteVacancies()
    }
}