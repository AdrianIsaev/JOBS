package ru.bestapps.data.data.repository

import ru.bestapps.data.data.contracts.OfferApi
import ru.bestapps.data.data.storage.models.Address
import ru.bestapps.data.data.storage.models.CustomButton
import ru.bestapps.data.data.storage.models.Offer
import ru.bestapps.data.data.storage.models.Vacancy
import ru.bestapps.domain.models.AddressDomain
import ru.bestapps.domain.models.CustomButtonDomain
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.OfferRepository

class OfferRepositoryImpl(private val retrofit: OfferApi) :OfferRepository{
    override suspend fun getOffers(): List<OfferDomain> {
        val response = retrofit.getOffers()
        val domainLayerModelList = mutableListOf<OfferDomain>()
        for (i in response.offers) {
            domainLayerModelList.add(helper(i))
        }
        return domainLayerModelList
    }

    private fun helper(offer: Offer): OfferDomain {

        return OfferDomain(
            id = offer.id,
            title = offer.title,
            link = offer.link,
            button = offer.button?.toDomain()
        )
    }
    private fun CustomButton.toDomain(): CustomButtonDomain {
        return CustomButtonDomain(
            text = this.text
        )
    }
}