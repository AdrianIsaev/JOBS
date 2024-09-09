package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.repository.OfferRepository

class GetOffersListUseCase(private val getOfferRepository: OfferRepository) {
    suspend fun execute(): List<OfferDomain>{
        return getOfferRepository.getOffers()
    }
}