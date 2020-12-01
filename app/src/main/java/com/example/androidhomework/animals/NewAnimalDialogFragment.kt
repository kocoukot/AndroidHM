package com.example.androidhomework.animals

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.animal_dialog.*

class NewAnimalDialogFragment : DialogFragment() {
    private var isAnimalRare = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.animal_dialog, container, false)
        val isRare = dialogView.findViewById<CheckBox>(R.id.isRare)
        val addAnimalBTN = dialogView.findViewById<Button>(R.id.addAnimal)
        val cancelBTN = dialogView.findViewById<Button>(R.id.cancelDailog)
        val animalName = dialogView.findViewById<EditText>(R.id.animalNameEditText)
        val animalFamily = dialogView.findViewById<EditText>(R.id.animalFamilyEditText)
        val animalRarity = dialogView.findViewById<EditText>(R.id.animalRarityEditText)
        val fieldsList = listOf<EditText>(animalName, animalFamily, animalRarity)

        fieldsList.forEach {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    btnCheck()
                }
            })
        }


        addAnimalBTN.setOnClickListener {
            (requireParentFragment() as AnimalsListFragment).addAnimalFromDialog(
                name = animalNameEditText.text.toString(),
                family = animalFamilyEditText.text.toString(),
                rarity = animalRarityEditText.text.toString(),
                isRare = isAnimalRare
            )
            dismiss()
        }

        cancelBTN.setOnClickListener {
            dismiss()
        }

        isRare.setOnClickListener {
            isAnimalRare = !isAnimalRare
            animalRarity.isVisible = isAnimalRare
            btnCheck()
            animalRarity.setText("")
        }
        return dialogView
    }

    private fun btnCheck() {
        if (!isAnimalRare) {
            addAnimal.isEnabled =
                animalNameEditText.text.isNotEmpty() && animalFamilyEditText.text.isNotEmpty()
        } else {
            addAnimal.isEnabled =
                animalNameEditText.text.isNotEmpty() && animalFamilyEditText.text.isNotEmpty() && animalRarityEditText.text.isNotEmpty()
        }
    }

}

