package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bestapps.data.data.repository.EmailRepositoryImpl
import ru.bestapps.data.data.storage.datasources.SharedPreferencesEmailStorage
import ru.bestapps.domain.models.SaveUserEmail
import ru.bestapps.domain.usecase.GetUserEmailUseCase
import ru.bestapps.domain.usecase.SaveUserEmailUseCase

class BufferViewModel(private val saveUserEmailUseCase: SaveUserEmailUseCase) : ViewModel(){

    val userEmail = MutableLiveData<String>()

    fun saveEmail(email:String){
        val saveUserEmail = SaveUserEmail(email = email)
        saveUserEmailUseCase.execute(saveUserEmail)
        userEmail.value = saveUserEmail.email
    }
}