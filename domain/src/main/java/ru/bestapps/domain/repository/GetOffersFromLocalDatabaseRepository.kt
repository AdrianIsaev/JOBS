package ru.bestapps.domain.repository

import ru.bestapps.domain.models.OfferDomain

interface GetOffersFromLocalDatabaseRepository{
    suspend fun getOffersFromLocalDb(): List<OfferDomain>
}