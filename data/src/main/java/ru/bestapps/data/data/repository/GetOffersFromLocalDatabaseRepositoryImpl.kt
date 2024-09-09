package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.models.CustomButton

import ru.bestapps.data.data.storage.room.dao.GetOffersDao
import ru.bestapps.data.data.storage.room.entity.Offer
import ru.bestapps.domain.models.CustomButtonDomain
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.repository.GetOffersFromLocalDatabaseRepository

class GetOffersFromLocalDatabaseRepositoryImpl(private val getOffersDao: GetOffersDao): GetOffersFromLocalDatabaseRepository {
    override suspend fun getOffersFromLocalDb(): List<OfferDomain> {
        val listRoomOffers = getOffersDao.getOffers()
        val listDomainOffers = mutableListOf<OfferDomain>()
        for (offer in listRoomOffers){
            listDomainOffers.add(helper(offer))
        }
        return listDomainOffers
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