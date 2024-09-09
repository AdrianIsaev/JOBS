package ru.bestapps.data.data.contracts

import retrofit2.http.GET
import ru.bestapps.data.data.storage.models.Offer
import ru.bestapps.data.data.storage.models.OffersResponse

interface OfferApi {
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getOffers(): OffersResponse
}