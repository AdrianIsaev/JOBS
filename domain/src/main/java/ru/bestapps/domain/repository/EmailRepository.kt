package ru.bestapps.domain.repository

import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.models.UserEmail

interface EmailRepository {
    fun saveUserEmail(saveUserEmail: SaveUserEmail)

    fun getEmail() : UserEmail
}