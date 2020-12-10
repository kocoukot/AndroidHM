package com.example.androidhomework.animals

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalsListViewModel : ViewModel() {

    private val animalRepository = AnimalsListRepository()
    private val animalLiveData = MutableLiveData<List<Animals>>(animalRepository.getAnimalList())
    private val showToastLiveData = SingleLiveEvent<Unit>()

    val animals: LiveData<List<Animals>>
        get() = animalLiveData

    val showToast: LiveData<Unit>
        get ()  = showToastLiveData



    fun addAnimal(name: String, family: String, rarity: String = "", isRare: Boolean = false) {
        val updatedList = animalRepository.addAnimalFromDialog(name, family, rarity, isRare)
        animalLiveData.postValue(updatedList)
    }

    fun deleteAnimal(position: Int) {
        val updatedList = animalRepository.deleteAnimal(animalLiveData.value!!, position)
        animalLiveData.postValue(updatedList)
        showToastLiveData.postValue(Unit)
    }


}