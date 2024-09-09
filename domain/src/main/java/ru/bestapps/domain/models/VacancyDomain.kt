package ru.bestapps.domain.models

data class VacancyDomain(
    val ids: Int,
    val id: String?,
    val lookingNumber: Int?,
    val title: String?,
    val address: AddressDomain?,
    val company: String?,
    val experience: ExperienceDomain?,
    val publishedDate: String?,
    val salary: SalaryDomain?,
    val schedules: List<String>?,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>?,
    var isFavorite: Boolean
)