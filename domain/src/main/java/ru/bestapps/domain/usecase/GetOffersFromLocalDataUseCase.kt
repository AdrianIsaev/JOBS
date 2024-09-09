package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.repository.GetOffersFromLocalDatabaseRepository

class GetOffersFromLocalDataUseCase(private val getOffersFromLocalDatabaseRepository: GetOffersFromLocalDatabaseRepository) {
    suspend fun execute(): List<OfferDomain>{
        return getOffersFromLocalDatabaseRepository.getOffersFromLocalDb()
    }
}