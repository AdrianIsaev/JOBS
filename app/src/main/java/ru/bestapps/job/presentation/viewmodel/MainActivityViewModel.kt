package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.usecase.DeleteAllVacanciesFromLocalDbUseCase
import ru.bestapps.domain.usecase.GetOffersListUseCase
import ru.bestapps.domain.usecase.GetVacanciesListUseCase
import ru.bestapps.domain.usecase.SaveLoadedOffersInLocalDbUseCase
import ru.bestapps.domain.usecase.SaveLoadedVacanciesInLocalDbUseCase

class MainActivityViewModel(private val saveLoadedVacanciesInLocalDbUseCase: SaveLoadedVacanciesInLocalDbUseCase,
    private val getOffersListUseCase: GetOffersListUseCase,
                            private val getVacanciesListUseCase: GetVacanciesListUseCase,
    private val saveLoadedOffersInLocalDbUseCase: SaveLoadedOffersInLocalDbUseCase

    ):ViewModel() {

    suspend fun loadVacanciesList(): List<VacancyDomain> {
        return getVacanciesListUseCase.execute()
    }
    suspend fun saveLoadedVacanciesInRoom(vacancies: List<VacancyDomain>){
        saveLoadedVacanciesInLocalDbUseCase.execute(vacancies)
    }
    suspend fun loadOffersList(): List<OfferDomain>{
        return getOffersListUseCase.execute()
    }
    suspend fun saveLoadedOffersInRoom(offers: List<OfferDomain>){
        saveLoadedOffersInLocalDbUseCase.execute(offers)
    }

}