package ru.bestapps.job.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bestapps.domain.usecase.GetUserEmailUseCase

class CheckViewModel(private val getUserEmailUseCase: GetUserEmailUseCase): ViewModel() {

    val userEmail = MutableLiveData<String>()

    fun getUserEmail(){
        userEmail.value = getUserEmailUseCase.execute().userEmail
    }
}