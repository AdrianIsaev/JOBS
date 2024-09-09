package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.datasources.DataStorage
import ru.bestapps.domain.models.Text
import ru.bestapps.domain.repository.LoadedDataFlagRepository

class LoadedDataFlagRepositoryImpl(private val sharedPreferencesEmailStorage: DataStorage):
    LoadedDataFlagRepository {

    override fun saveUserText(text: Text){
        val text = ru.bestapps.data.data.storage.models.Text(text = text.text.toString())
        sharedPreferencesEmailStorage.save(text)
    }

    override fun getText(): String {
        val text = sharedPreferencesEmailStorage.getText()
        return text.text
    }
}