package ru.bestapps.data.data.storage.datasources


import android.content.Context
import ru.bestapps.data.data.storage.models.EmailModel
import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.models.UserEmail

private const val SHARED_PREFS_NAME = "main_shared_preferences"
private const val KEY_NAME = "user_email"
private const val EMPTY_EMAIL = "error"

class SharedPreferencesEmailStorage(context: Context): EmailStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(emailModel: EmailModel) {
        sharedPreferences.edit().putString(KEY_NAME, emailModel.email).apply()
    }

    override fun getEmail(): EmailModel {
        val userEmail = sharedPreferences.getString(KEY_NAME, EMPTY_EMAIL)?: EMPTY_EMAIL
        return EmailModel(userEmail)
    }
}