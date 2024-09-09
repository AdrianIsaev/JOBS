package ru.bestapps.job.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bestapps.job.presentation.viewmodel.BufferViewModel
import ru.bestapps.job.presentation.viewmodel.CheckViewModel
import ru.bestapps.job.presentation.viewmodel.ClientFavoritesViewModel
import ru.bestapps.job.presentation.viewmodel.MainActivityViewModel
import ru.bestapps.job.presentation.viewmodel.SearchViewModel
import ru.bestapps.job.presentation.viewmodel.VacancyPageViewModel

val appModule = module {
    viewModel<BufferViewModel> {
        BufferViewModel(saveUserEmailUseCase = get())
    }
    viewModel<CheckViewModel>{
        CheckViewModel(getUserEmailUseCase = get())
    }
    viewModel<MainActivityViewModel>{ MainActivityViewModel(
            getVacanciesListUseCase = get(),
            getOffersListUseCase = get(),
            saveLoadedVacanciesInLocalDbUseCase = get(),
            saveLoadedOffersInLocalDbUseCase = get()
        ) }
    viewModel<SearchViewModel>{
        SearchViewModel(getVacanciesFromLocalDataUseCase = get(),
            getOffersFromLocalDataUseCase = get(),
            setVacancyFavoriteUseCase = get(),
            loadedDataFlagUseCase = get())
    }
    viewModel<ClientFavoritesViewModel>{
        ClientFavoritesViewModel(getVacanciesFromLocalDataUseCase = get())
    }
    viewModel<VacancyPageViewModel>{
        VacancyPageViewModel(setVacancyFavoriteUseCase = get(), getCurrentVacancyUseCase = get())
    }


}