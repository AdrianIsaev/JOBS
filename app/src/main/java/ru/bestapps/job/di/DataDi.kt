package ru.bestapps.job.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bestapps.data.data.contracts.OfferApi
import ru.bestapps.data.data.contracts.VacancyApi
import ru.bestapps.data.data.repository.DeleteVacanciesFromLocalDbRepositoryImpl
import ru.bestapps.data.data.repository.EmailRepositoryImpl
import ru.bestapps.data.data.repository.GetCurrentVacancyRepositoryImpl
import ru.bestapps.data.data.repository.GetOffersFromLocalDatabaseRepositoryImpl
import ru.bestapps.data.data.repository.GetVacanciesFromLocalDatabaseRepositoryImpl
import ru.bestapps.data.data.repository.LoadedDataFlagRepositoryImpl
import ru.bestapps.data.data.repository.OfferRepositoryImpl
import ru.bestapps.data.data.repository.SaveOffersInLocalDbRepositoryImpl
import ru.bestapps.data.data.repository.SaveVacanciesInLocalDbRepositoryImpl
import ru.bestapps.data.data.repository.SetVacancyFavoriteRepositoryImpl
import ru.bestapps.data.data.repository.VacancyRepositoryImpl
import ru.bestapps.data.data.storage.datasources.DataStorage
import ru.bestapps.data.data.storage.datasources.EmailStorage
import ru.bestapps.data.data.storage.datasources.SharedPreferencesEmailStorage
import ru.bestapps.data.data.storage.datasources.SharesPreferencesDataStorage
import ru.bestapps.data.data.storage.room.root.AppLocalDatabase
import ru.bestapps.domain.repository.DeleteVacanciesFromLocalDbRepository
import ru.bestapps.domain.repository.VacancyRepository
import ru.bestapps.domain.repository.EmailRepository
import ru.bestapps.domain.repository.GetCurrentVacancyRepository
import ru.bestapps.domain.repository.GetOffersFromLocalDatabaseRepository
import ru.bestapps.domain.repository.GetVacanciesFromLocalDatabaseRepository
import ru.bestapps.domain.repository.LoadedDataFlagRepository
import ru.bestapps.domain.repository.OfferRepository
import ru.bestapps.domain.repository.SaveOffersInLocalDbRepository
import ru.bestapps.domain.repository.SaveVacanciesInLocalDbRepository
import ru.bestapps.domain.repository.SetVacancyFavoriteRepository
import ru.bestapps.job.app.App

val dataModule = module {

    single<EmailStorage>{
        SharedPreferencesEmailStorage(context = get())
    }

    single<EmailRepository>{
        EmailRepositoryImpl(sharedPreferencesEmailStorage = get())
    }
    single<DataStorage> {
        SharesPreferencesDataStorage(context = get())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(VacancyApi::class.java)
    }

    single {
        get<Retrofit>().create(OfferApi::class.java)
    }

    single<VacancyRepository>{
        VacancyRepositoryImpl(retrofit = get())
    }
    single<OfferRepository>{
        OfferRepositoryImpl(retrofit = get())
    }
    single<GetOffersFromLocalDatabaseRepository>{
        GetOffersFromLocalDatabaseRepositoryImpl(getOffersDao = get())
    }
    single<SaveOffersInLocalDbRepository>{
        SaveOffersInLocalDbRepositoryImpl(saveOffersDao = get())
    }
    single<SetVacancyFavoriteRepository>{
        SetVacancyFavoriteRepositoryImpl(setFavoriteDao = get())
    }
    single<GetCurrentVacancyRepository> {
        GetCurrentVacancyRepositoryImpl(getVacancyDao = get())
    }
    single<LoadedDataFlagRepository> {
        LoadedDataFlagRepositoryImpl(sharedPreferencesEmailStorage = get())
    }





    single<DeleteVacanciesFromLocalDbRepository> { DeleteVacanciesFromLocalDbRepositoryImpl(deleteVacanciesDao = get()) }
    single<SaveVacanciesInLocalDbRepository>{SaveVacanciesInLocalDbRepositoryImpl(saveVacanciesDao = get())}
    single<GetVacanciesFromLocalDatabaseRepository>{GetVacanciesFromLocalDatabaseRepositoryImpl(getVacanciesDao = get())}



    single { AppLocalDatabase.getDatabase(androidContext()) }
    single { get<AppLocalDatabase>().getVacanciesDao() }
    single { get<AppLocalDatabase>().saveVacanciesDao() }
    single { get<AppLocalDatabase>().deleteVacanciesDao() }
    single { get<AppLocalDatabase>().getOffersDao()}
    single { get<AppLocalDatabase>().saveOffersDao()}
    single { get<AppLocalDatabase>().setFavoriteDao() }
    single { get<AppLocalDatabase>().getVacancyDao() }
}
