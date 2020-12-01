package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.androidhomework.adapters.AnimalAdapter
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import kotlinx.android.synthetic.main.list_fragment.*
import kotlin.random.Random

class AnimalsListFragment(
    val listType: Int
) : Fragment(R.layout.list_fragment) {

    private val KEY_LIST_SIZE = "KEY_LIST_SIZE"

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

    private var animalAdapter: AnimalAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("module17",listType.toString() )
        initLIst()
        if (savedInstanceState == null ){
            animalAdapter?.items = animalsList
        } else {
            animalsList = listOf()
            val state = savedInstanceState.getParcelableArray(KEY_LIST_SIZE)
            for (a in state!!.iterator()) {
                animalsList = animalsList + listOf(a as Animals)
            }

        }
        listCheckIfEmpty()
        animalAdapter?.items = animalsList
        addFAB.setOnClickListener {
            NewAnimalDialogFragment().show(childFragmentManager, "NewAnimalDialogTag")
        }

    }

    private fun initLIst() {
        animalAdapter = AnimalAdapter { position -> deleteAnimal(position) }
        with(animalRecyclerView) {
            adapter = animalAdapter
            when (listType){
                0 -> layoutManager = LinearLayoutManager(requireContext())
                1 ->  layoutManager = GridLayoutManager(requireContext(), 4).apply {
                    orientation = GridLayoutManager.HORIZONTAL
                }
                2 ->  layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            }
            setHasFixedSize(true)
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            itemAnimator =  FlipInTopXAnimator()
        }
    }

    private fun listCheckIfEmpty(){
        emptyListTextView.isVisible = animalsList.isEmpty()
    }

    private fun deleteAnimal(position: Int){
        animalsList = animalsList.filterIndexed{ index, animal -> index != position}
        animalAdapter?.items = animalsList
        listCheckIfEmpty()
    }

    fun addAnimalFromDialog(name: String, family: String, rarity: String = "", isRare: Boolean = false ){
        animalsList = if (!isRare){
            val newCommon = Animals.Common(id = Random.nextLong(),name = name, imageLink = "", familyType = family)
            listOf(newCommon) + animalsList
        } else {
            val newAnimal = Animals.Rare(id = Random.nextLong(),name = name, imageLink = "", familyType = family,rarity = rarity)
            listOf(newAnimal) + animalsList
        }
        animalAdapter?.items = animalsList

        animalRecyclerView.scrollToPosition(0)
        listCheckIfEmpty()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_LIST_SIZE, animalsList.toTypedArray())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animalAdapter = null
    }
}