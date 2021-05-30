package com.example.androidhomework.material

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.ItemOffsetDecoration
import com.example.androidhomework.R
import com.example.androidhomework.material.adapter.ItemAdapter
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import kotlinx.android.synthetic.main.fragment_material_desing.*

class ShopFragment : Fragment(R.layout.fragment_material_desing) {

    private var itemShopAdapter: ItemAdapter? = null

    private var itemsShopList = listOf(
        ItemShop(
            id = 1,
            itemImage = R.drawable.bag,
            itemName = "Сумка",
            itemPrice = "5000 руб"

        ),
        ItemShop(
            id = 2,
            itemImage = R.drawable.glasses,
            itemName = "Очки",
            itemPrice = "1200 руб"

        ),
        ItemShop(
            id = 3,
            itemImage = R.drawable.shoose,
            itemName = "Туфли",
            itemPrice = "10000 руб"

        ),
        ItemShop(
            id = 4,
            itemImage = R.drawable.cap,
            itemName = "Кепка",
            itemPrice = "2300 руб"

        ),
        ItemShop(
            id = 5,
            itemImage = R.drawable.shirt,
            itemName = "Майка",
            itemPrice = "700 руб"

        ),
        ItemShop(
            id = 6,
            itemImage = R.drawable.jeans,
            itemName = "Джинсы",
            itemPrice = "9300 руб"

        ),
        ItemShop(
            id = 7,
            itemImage = R.drawable.shtani,
            itemName = "Штаны",
            itemPrice = "3300 руб"
        )
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        showSnackbar()
       // fragmentViewSnack.snack("connection failed")
    }


    private fun initList() {
        itemShopAdapter = ItemAdapter()

        with(itemShopRecycler) {
            Log.d("module32", "get here ")
            adapter = itemShopAdapter
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                orientation = GridLayoutManager.VERTICAL
                val dividerItemDecoration =
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                addItemDecoration(dividerItemDecoration)
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))

                addItemDecoration(ItemOffsetDecoration(requireContext()))
                setHasFixedSize(true)

            }
            itemShopAdapter?.items = itemsShopList
        }
    }

    fun showSnackbar(){
        Snackbar.make(fragmentViewSnack, "Connection failed. Saved items shown", Snackbar.LENGTH_SHORT)
            .setAction("Refresh"){
                Snackbar.make(fragmentViewSnack, "List updated", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }

}

