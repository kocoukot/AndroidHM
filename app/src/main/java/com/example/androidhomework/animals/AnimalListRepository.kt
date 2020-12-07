package com.example.androidhomework.animals

import android.util.Log
import kotlin.math.log
import kotlin.random.Random

class AnimalListRepository {

    private var animalsList = listOf(
        Animals.Rare(
            id = 1,
            name = "Огарь",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/A_couple_of_Tadorna_ferruginea.2.jpg/550px-A_couple_of_Tadorna_ferruginea.2.jpg",
            familyType = "Утиные",
            rarity = "Редкий вид"
        ),
        Animals.Common(
            id = 2,
            name = "Олень",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/d/d9/Family_Cervidae_five_species.jpg",
            familyType = "Оленевые"
        ),
        Animals.Rare(
            id = 3,
            name = "Пеструшка степная",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Lagurus_lagurus.jpg/550px-Lagurus_lagurus.jpg",
            familyType = "Хомяковые",
            rarity = "Крайне редкий вид"
        ),
        Animals.Rare(
            id = 4,
            name = "Лунь степной",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Circus_macrourus.jpg/550px-Circus_macrourus.jpg",
            familyType = "Ястребиные",
            rarity = "Редкий вид"
        ),
        Animals.Common(
            id = 5,
            name = "Слон",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Elephant_Diversity.jpg/2560px-Elephant_Diversity.jpg",
            familyType = "Слоновые"
        )
    )

    fun getAnimalList() = animalsList

    fun deleteAnimal(animalsList: List<Animals>, position: Int): List<Animals> {
        return animalsList.filterIndexed { index, _ -> index != position }
    }

    fun addAnimalFromDialog(
        name: String,
        family: String,
        rarity: String = "",
        isRare: Boolean = false
    ): Animals {
        return if (!isRare) {
            val newCommon = Animals.Common(
                id = Random.nextLong(),
                name = name,
                imageLink = "",
                familyType = family
            )
            (listOf(newCommon) + animalsList).first()
        } else {
            val newAnimal = Animals.Rare(
                id = Random.nextLong(),
                name = name,
                imageLink = "",
                familyType = family,
                rarity = rarity
            )
            (listOf(newAnimal) + animalsList).first()
        }
    }

}