package ru.bestapps.domain.usecase


import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.repository.EmailRepository

class SaveUserEmailUseCase(private val emailRepository: EmailRepository) {
    fun execute(saveUserEmail: SaveUserEmail){
        emailRepository.saveUserEmail(SaveUserEmail(saveUserEmail.email))
    }
}