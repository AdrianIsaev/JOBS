package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.datasources.EmailStorage
import ru.bestapps.data.data.storage.models.EmailModel
import ru.bestapps.data.data.storage.datasources.SharedPreferencesEmailStorage
import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.models.UserEmail
import ru.bestapps.domain.repository.EmailRepository


class EmailRepositoryImpl(private val sharedPreferencesEmailStorage: EmailStorage): EmailRepository {


    override fun saveUserEmail(saveUserEmail: SaveUserEmail){
        val emailModel = EmailModel(email = saveUserEmail.email)
        sharedPreferencesEmailStorage.save(emailModel)
    }

    override fun getEmail(): UserEmail {
        val email = sharedPreferencesEmailStorage.getEmail()
        val userEmail = UserEmail(userEmail = email.email)
        return userEmail
    }

}