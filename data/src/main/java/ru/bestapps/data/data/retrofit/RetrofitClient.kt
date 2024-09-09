package ru.bestapps.data.data.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bestapps.data.data.contracts.OfferApi
import ru.bestapps.data.data.contracts.VacancyApi
import ru.bestapps.data.data.storage.models.Offer
import ru.bestapps.data.data.storage.models.Vacancy

class RetrofitClient() {
    private val BASE_URL = "https://drive.usercontent.google.com/u/0/"
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    suspend fun executeVacancyApi(callback: (List<Vacancy>) -> Unit){
        val api = retrofit.create(VacancyApi::class.java)
        val response = api.getVacancies()
        val listOfVacancies = mutableListOf<Vacancy>()
        for (i in response.vacancies){
            listOfVacancies.add(i)
        }
        withContext(Dispatchers.Main){
            callback(listOfVacancies)
        }
    }
    suspend fun executeOfferApi(callback: (List<Offer>) -> Unit){
        val api = retrofit.create(OfferApi::class.java)
        val offers = api.getOffers()
        withContext(Dispatchers.Main){
            callback(offers.offers)
        }
    }
}