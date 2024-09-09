package ru.bestapps.data.data.storage.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.bestapps.data.data.storage.models.CustomButton

@Entity(tableName = "offers")
data class Offer(
    @PrimaryKey(autoGenerate = true)
    val ids: Int,
    val id: String?,
    val title: String?,
    val link: String?,
    @Embedded val button: CustomButton? = null
)