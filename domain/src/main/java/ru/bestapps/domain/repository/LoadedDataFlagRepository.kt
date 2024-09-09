package ru.bestapps.domain.repository

import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.models.Text
import ru.bestapps.domain.models.UserEmail

interface LoadedDataFlagRepository {
    fun saveUserText(text: Text)

    fun getText() : String
}