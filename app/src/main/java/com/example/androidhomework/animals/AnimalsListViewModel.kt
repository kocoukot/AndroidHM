package com.example.androidhomework.animals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalsListViewModel : ViewModel() {

    private val animalRepository = AnimalListRepository()
    private val animalLiveData = MutableLiveData<List<Animals>>(animalRepository.getAnimalList())

    fun animals(): LiveData<List<Animals>> = animalLiveData

    fun addAnimal(name: String, family: String, rarity: String = "", isRare: Boolean = false) {
        val updatedList = listOf(
            animalRepository.addAnimalFromDialog(
                name,
                family,
                rarity,
                isRare
            )
        ) + animalLiveData.value!!
        animalLiveData.postValue(updatedList)
    }

    fun deleteAnimal(position: Int) {
        val updatedList = animalRepository.deleteAnimal(animalLiveData.value!!, position)
        animalLiveData.postValue(updatedList)
    }
}