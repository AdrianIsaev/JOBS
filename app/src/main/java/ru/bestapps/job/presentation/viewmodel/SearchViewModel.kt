package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.models.Text
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.usecase.GetOffersFromLocalDataUseCase
import ru.bestapps.domain.usecase.GetVacanciesListUseCase
import ru.bestapps.domain.usecase.GetVacanciesFromLocalDataUseCase
import ru.bestapps.domain.usecase.LoadedDataFlagUseCase
import ru.bestapps.domain.usecase.SetVacancyFavoriteUseCase

class SearchViewModel(private val getVacanciesFromLocalDataUseCase: GetVacanciesFromLocalDataUseCase,
    private val getOffersFromLocalDataUseCase: GetOffersFromLocalDataUseCase,
    private val setVacancyFavoriteUseCase: SetVacancyFavoriteUseCase,
    private val loadedDataFlagUseCase: LoadedDataFlagUseCase
    ):
    ViewModel() {

    suspend fun getVacancies(): List<VacancyDomain> {
        return getVacanciesFromLocalDataUseCase.execute()
    }
    suspend fun getOffers(): List<OfferDomain>{
        return getOffersFromLocalDataUseCase.execute()
    }
    suspend fun setFavorite(id:Int, isF: Boolean){
        setVacancyFavoriteUseCase.execute(id , isF)
    }
    fun saveFlag(text: Text){
        loadedDataFlagUseCase.saveFlag(text)
    }
    fun loadFlag(): String{
        return loadedDataFlagUseCase.getFlag()
    }
}