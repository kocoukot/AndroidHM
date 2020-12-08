package com.example.androidhomework.animals

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.androidhomework.ItemOffsetDecoration
import com.example.androidhomework.R
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import kotlinx.android.synthetic.main.list_fragment.*
import kotlin.random.Random

class AnimalsListFragment : Fragment(R.layout.list_fragment) {

    private val animalListViewModel: AnimalsListViewModel by viewModels()
    private var animalAdapter: AnimalAdapter? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLIst()
        observeViewModelState()
        addFAB.setOnClickListener {
            NewAnimalDialogFragment().show(childFragmentManager, "NewAnimalDialogTag")
        }
    }

    private fun initLIst() {
        animalAdapter = AnimalAdapter({ name, family ->
           val actions = AnimalsListFragmentDirections.actionAnimalsListFragmentToAnimalDetailFragment(name, family)
            findNavController().navigate(actions)
        }, { position: Int -> deleteAnimal(position) })


        with(animalRecyclerView) {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            addItemDecoration(
                ItemOffsetDecoration(
                    requireContext()
                )
            )
            itemAnimator = FlipInTopXAnimator()
        }
    }

    private fun deleteAnimal(position: Int) {
        animalListViewModel.deleteAnimal(position)
    }

    private fun observeViewModelState() {
        animalListViewModel.animals.observe(viewLifecycleOwner) { newAnimal ->
            animalAdapter?.items = newAnimal
            emptyListTextView.isVisible = newAnimal.isEmpty()
        }

        animalListViewModel.showToast
            .observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), "Объект удален", Toast.LENGTH_SHORT).show()
            }


    }


    fun addAnimalFromDialog(
        name: String,
        family: String,
        rarity: String = "",
        isRare: Boolean = false
    ) {
        animalListViewModel.addAnimal(name, family, rarity, isRare)
        animalRecyclerView.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animalAdapter = null
    }
}