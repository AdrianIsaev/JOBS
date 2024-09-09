package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.repository.SaveOffersInLocalDbRepository

class SaveLoadedOffersInLocalDbUseCase(private val saveOffersInLocalDbRepository: SaveOffersInLocalDbRepository) {
    suspend fun execute(offersList: List<OfferDomain>){
        saveOffersInLocalDbRepository.saveOffersInLocalDb(offersList)
    }
}