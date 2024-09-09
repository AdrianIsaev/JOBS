package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.models.CustomButton
import ru.bestapps.data.data.storage.room.dao.SaveOffersDao
import ru.bestapps.data.data.storage.room.entity.Offer
import ru.bestapps.domain.models.CustomButtonDomain
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.repository.SaveOffersInLocalDbRepository

class SaveOffersInLocalDbRepositoryImpl(private val saveOffersDao: SaveOffersDao): SaveOffersInLocalDbRepository {
    override suspend fun saveOffersInLocalDb(offersDomain: List<OfferDomain>){
        for (offer in offersDomain) {
            saveOffersDao.insertOffer(helper(offer))
        }
    }
    private fun helper(offerDomain: OfferDomain): Offer {

        return Offer(
            id = offerDomain.id,
            title = offerDomain.title,
            link = offerDomain.link,
            ids = 0,
            button = offerDomain.button?.toRoom()

        )
    }
    private fun CustomButtonDomain.toRoom(): CustomButton {
        return CustomButton(
            text = this.text
        )
    }
}