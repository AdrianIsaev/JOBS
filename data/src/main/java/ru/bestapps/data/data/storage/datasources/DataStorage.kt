package ru.bestapps.data.data.storage.datasources

import ru.bestapps.data.data.storage.models.Text


interface DataStorage {
    fun save(text: ru.bestapps.data.data.storage.models.Text)

    fun getText(): Text
}