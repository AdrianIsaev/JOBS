package ru.bestapps.data.data.storage.datasources

import android.content.Context
import ru.bestapps.data.data.storage.models.Text

private const val SHARED_PREFS_FLAG = "flag_shared_preferences"
private const val KEY_NAME = "flag"
private const val EMPTY_FLAG = "error"


class SharesPreferencesDataStorage(context: Context): DataStorage {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FLAG, Context.MODE_PRIVATE)
    override fun save(text: ru.bestapps.data.data.storage.models.Text) {
        sharedPreferences.edit().putString(KEY_NAME, text.text).apply()
    }

    override fun getText(): Text {
        val text: String = sharedPreferences.getString(KEY_NAME, EMPTY_FLAG)?: EMPTY_FLAG
        return Text(text)
    }
}