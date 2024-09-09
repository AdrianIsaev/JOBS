package ru.bestapps.domain.models

data class OfferDomain(
    val id: String?,
    val title: String?,
    val link: String?,
    val button: CustomButtonDomain? = null
)

