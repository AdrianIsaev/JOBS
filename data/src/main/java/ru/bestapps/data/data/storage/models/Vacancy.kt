package ru.bestapps.data.data.storage.models

data class Vacancy(
    val id: String?,
    val lookingNumber: Int?,
    val title: String?,
    val address: Address?,
    val company: String?,
    val experience: Experience?,
    val publishedDate: String?,
    val salary: Salary?,
    val schedules: List<String>?,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>?
) {
}