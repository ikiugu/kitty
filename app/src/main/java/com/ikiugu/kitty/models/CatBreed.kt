package com.ikiugu.kitty.models

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

data class CatBreed (
    val weight: Weight,
    val id: String,
    val name: String,
    val cfaURL: String,
    val vetstreetURL: String,
    val vcahospitalsURL: String,
    val temperament: String,
    val origin: String,
    val countryCodes: String,
    val countryCode: String,
    val description: String,
    val lifeSpan: String,
    val indoor: Long,
    val lap: Long,
    val altNames: String,
    val adaptability: Long,
    val affectionLevel: Long,
    val childFriendly: Long,
    val dogFriendly: Long,
    val energyLevel: Long,
    val grooming: Long,
    val healthIssues: Long,
    val intelligence: Long,
    val sheddingLevel: Long,
    val socialNeeds: Long,
    val strangerFriendly: Long,
    val vocalisation: Long,
    val experimental: Long,
    val hairless: Long,
    val natural: Long,
    val rare: Long,
    val rex: Long,
    val suppressedTail: Long,
    val shortLegs: Long,
    val wikipediaURL: String,
    val hypoallergenic: Long,
    val referenceImageID: String,
    val image: Image
)

data class Image (
    val id: String,
    val width: Long,
    val height: Long,
    val url: String
)

data class Weight (
    val imperial: String,
    val metric: String
)
