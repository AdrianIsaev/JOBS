package ru.bestapps.domain.repository

import ru.bestapps.domain.models.OfferDomain

interface SaveOffersInLocalDbRepository {
    suspend fun saveOffersInLocalDb(offersDomainList: List<OfferDomain>)
}