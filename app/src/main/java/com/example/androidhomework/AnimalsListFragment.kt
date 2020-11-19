package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*
import kotlin.math.log

class AnimalsListFragment : Fragment(R.layout.list_fragment) {

    private val KEY_LIST_SIZE = "KEY_LIST_SIZE"

    private var animalsList = listOf(
        Animals.Rare(
            name = "Огарь",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/A_couple_of_Tadorna_ferruginea.2.jpg/550px-A_couple_of_Tadorna_ferruginea.2.jpg",
            familyType = "Утиные",
            rarity = "Редкий вид"
        ),
        Animals.Common(
            name = "Олень",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/d/d9/Family_Cervidae_five_species.jpg",
            familyType = "Оленевые"
        ),
        Animals.Rare(
            name = "Пеструшка степная",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Lagurus_lagurus.jpg/550px-Lagurus_lagurus.jpg",
            familyType = "Хомяковые",
            rarity = "Крайне редкий вид"
        ),
        Animals.Rare(
            name = "Лунь степной",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Circus_macrourus.jpg/550px-Circus_macrourus.jpg",
            familyType = "Ястребиные",
            rarity = "Редкий вид"
        ),
        Animals.Common(
            name = "Слон",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Elephant_Diversity.jpg/2560px-Elephant_Diversity.jpg",
            familyType = "Слоновые"
        )
    )

    private var animalAdapter: AnimalAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLIst()
        if (savedInstanceState == null ){
            animalAdapter?.updateAnimals(animalsList)
        } else {
            animalsList = listOf()
            for (i in 0 until savedInstanceState.getInt(KEY_LIST_SIZE, 0)){
               val animal = savedInstanceState.getStringArray("animal$i")
                if (animal!!.size == 3){
                    val animalCommom = Animals.Common(name = animal[0], imageLink = animal[1], familyType = animal[2])
                    animalsList =  animalsList  + listOf(animalCommom)

                } else {
                    val animalRare = Animals.Rare(name = animal[0], imageLink = animal[1], familyType = animal[2],rarity = animal[3])
                    animalsList = animalsList + listOf(animalRare)
                }
            }
        }
        animalAdapter?.updateAnimals(animalsList)
        addFAB.setOnClickListener {
            NewAnimalDialogFragment().show(childFragmentManager, "NewAnimalDialogTag")
        }

    }

    private fun initLIst() {
        animalAdapter = AnimalAdapter { position -> deleteAnimal(position) }
        with(animalRecyclerView) {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun listCheckIfEmpty(){
        emptyListTextView.isVisible = animalsList.isEmpty()
    }

    private fun deleteAnimal(position: Int){
        animalsList = animalsList.filterIndexed{ index, animal -> index != position}
        animalAdapter?.updateAnimals(animalsList)
        animalAdapter?.notifyItemRemoved(position)
        listCheckIfEmpty()
    }

    fun addAnimalFromDialog(name: String, family: String, rarity: String = "", isRare: Boolean = false ){
        animalsList = if (!isRare){
            val newCommon = Animals.Common(name = name, imageLink = "", familyType = family)
            listOf(newCommon) + animalsList
        } else {
            val newAnimal = Animals.Rare(name = name, imageLink = "", familyType = family,rarity = rarity)
            listOf(newAnimal) + animalsList
        }
        animalAdapter?.updateAnimals(animalsList)
        animalAdapter?.notifyItemInserted(0)
        animalRecyclerView.scrollToPosition(0)
        listCheckIfEmpty()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_LIST_SIZE, animalsList.size)
        for (i in animalsList.indices) {
            if (animalsList[i].let { it as? Animals.Rare } != null) {
                val animal = animalsList[i].let { it as? Animals.Rare } ?: error("")
                outState.putStringArray(
                    "animal$i",
                    arrayOf(animal.name, animal.imageLink, animal.familyType, animal.rarity)
                )
                continue
            } else {
                val animal = animalsList[i].let { it as? Animals.Common } ?: error("")
                outState.putStringArray(
                    "animal$i",
                    arrayOf(animal.name, animal.imageLink, animal.familyType)
                )
                continue
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        animalAdapter = null

    }


}