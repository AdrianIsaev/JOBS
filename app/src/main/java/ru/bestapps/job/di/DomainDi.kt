package ru.bestapps.job.di

import org.koin.dsl.module
import ru.bestapps.domain.usecase.DeleteAllVacanciesFromLocalDbUseCase
import ru.bestapps.domain.usecase.GetCurrentVacancyUseCase
import ru.bestapps.domain.usecase.GetOffersFromLocalDataUseCase
import ru.bestapps.domain.usecase.GetOffersListUseCase
import ru.bestapps.domain.usecase.GetUserEmailUseCase
import ru.bestapps.domain.usecase.GetVacanciesListUseCase
import ru.bestapps.domain.usecase.GetVacanciesFromLocalDataUseCase
import ru.bestapps.domain.usecase.LoadedDataFlagUseCase
import ru.bestapps.domain.usecase.SaveLoadedOffersInLocalDbUseCase
import ru.bestapps.domain.usecase.SaveLoadedVacanciesInLocalDbUseCase
import ru.bestapps.domain.usecase.SaveUserEmailUseCase
import ru.bestapps.domain.usecase.SetVacancyFavoriteUseCase

val domainModule = module{

    factory<GetUserEmailUseCase>{
        GetUserEmailUseCase(emailRepository = get())
    }
    factory<SaveUserEmailUseCase>{
        SaveUserEmailUseCase(emailRepository = get())
    }
    factory <GetVacanciesListUseCase>{
        GetVacanciesListUseCase(vacancyRepositoryImpl = get())
    }


    factory <GetVacanciesFromLocalDataUseCase>{
        GetVacanciesFromLocalDataUseCase(localDatabaseRepository = get())
    }
    factory<SaveLoadedVacanciesInLocalDbUseCase> {
        SaveLoadedVacanciesInLocalDbUseCase(saveVacanciesInLocalDbRepository = get())
    }
    factory<DeleteAllVacanciesFromLocalDbUseCase> {
        DeleteAllVacanciesFromLocalDbUseCase(deleteVacanciesFromLocalDbRepository = get())
    }
    factory {
        GetOffersListUseCase(getOfferRepository = get())
    }
    factory {
        GetOffersFromLocalDataUseCase(getOffersFromLocalDatabaseRepository = get())
    }
    factory {
        SaveLoadedOffersInLocalDbUseCase(saveOffersInLocalDbRepository = get())
    }
    factory {
        SetVacancyFavoriteUseCase(setVacancyFavoriteRepository = get())
    }
    factory {
        GetCurrentVacancyUseCase(getCurrentVacancyRepository = get())
    }
    factory {
        LoadedDataFlagUseCase(loadedDataFlagRepository = get())
    }

}