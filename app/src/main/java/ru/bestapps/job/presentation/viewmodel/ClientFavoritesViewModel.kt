package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.usecase.GetVacanciesFromLocalDataUseCase

class ClientFavoritesViewModel(private val getVacanciesFromLocalDataUseCase:
                               GetVacanciesFromLocalDataUseCase): ViewModel() {

    suspend fun getVacancies(): List<VacancyDomain>{
        return getVacanciesFromLocalDataUseCase.execute()
    }
}