package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.usecase.GetCurrentVacancyUseCase
import ru.bestapps.domain.usecase.SetVacancyFavoriteUseCase

class VacancyPageViewModel(private val setVacancyFavoriteUseCase: SetVacancyFavoriteUseCase,
    private val getCurrentVacancyUseCase: GetCurrentVacancyUseCase) :
    ViewModel() {

   suspend fun setFavourite(id: Int, isF: Boolean) {
        setVacancyFavoriteUseCase.execute(id, isF)
    }
    suspend fun getCurrentVacancy(id:Int): VacancyDomain{
        return getCurrentVacancyUseCase.execute(id)
    }
}