package com.example.androidhomework

sealed class Animals {

    data class Common(
        val name: String,
        val imageLink: String,
        val familyType: String
    ): Animals()

    data class Rare(
        val name: String,
        val imageLink: String,
        val familyType: String,
        val rarity: String
    ): Animals()


}