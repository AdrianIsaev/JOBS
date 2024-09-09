package ru.bestapps.domain.usecase

import ru.bestapps.domain.models.Text
import ru.bestapps.domain.repository.LoadedDataFlagRepository

class LoadedDataFlagUseCase(private val loadedDataFlagRepository: LoadedDataFlagRepository) {
    fun saveFlag(text: Text){
        loadedDataFlagRepository.saveUserText(text)
    }
    fun getFlag(): String{
        return loadedDataFlagRepository.getText()
    }
}