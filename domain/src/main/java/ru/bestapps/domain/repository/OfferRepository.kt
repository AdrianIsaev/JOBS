package ru.bestapps.domain.repository

import ru.bestapps.domain.models.OfferDomain

interface OfferRepository {
    suspend fun getOffers(): List<OfferDomain>
}