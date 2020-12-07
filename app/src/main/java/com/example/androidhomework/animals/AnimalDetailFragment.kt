package com.example.androidhomework.animals

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.detail_animal_info.*

class AnimalDetailFragment:Fragment(R.layout.detail_animal_info) {
    private val animalRepository = AnimalListRepository()

    private val args: AnimalDetailFragmentArgs by navArgs()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val test = animalRepository.getAnimalList()[1]
        detailAnimalName.text = args.animalName
        detailAnimalFamily.text = args.animalFamily
    }

}