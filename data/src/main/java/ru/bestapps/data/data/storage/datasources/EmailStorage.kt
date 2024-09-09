package ru.bestapps.data.data.storage.datasources

import ru.bestapps.data.data.storage.models.EmailModel
import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.models.UserEmail

interface EmailStorage {

    fun save(emailModel: EmailModel)

    fun getEmail(): EmailModel

}