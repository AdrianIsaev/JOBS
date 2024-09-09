package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.UserEmail
import ru.bestapps.domain.repository.EmailRepository

class GetUserEmailUseCase(private val emailRepository: EmailRepository) {

    fun execute(): UserEmail{
        return emailRepository.getEmail()
    }

}