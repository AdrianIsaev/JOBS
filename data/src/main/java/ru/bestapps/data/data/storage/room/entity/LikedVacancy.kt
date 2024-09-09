package ru.bestapps.data.data.storage.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.bestapps.data.data.storage.models.Address
import ru.bestapps.data.data.storage.models.Experience
import ru.bestapps.data.data.storage.models.Salary

@Entity(tableName = "liked_vacancies")
data class LikedVacancy(
    @PrimaryKey(autoGenerate = true)
    val ids: Int,
    val id: String?,
    val lookingNumber: Int?,
    val title: String?,
    @Embedded val address: Address?,
    val company: String?,
    @Embedded val experience: Experience?,
    val publishedDate: String?,
    @Embedded val salary: Salary?,
    val schedules: List<String>?,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>?
)