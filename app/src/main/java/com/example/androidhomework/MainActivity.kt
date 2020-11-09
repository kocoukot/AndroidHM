package com.example.androidhomework

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var state: FormState = FormState(valid = true, message = "")
    private val KEY_PARC = "ParcKey"
    var checkedItems = booleanArrayOf(true, true, true, true)
    var filterScreens: List<FragmentScreen> = listOf()
    private val screens: List<FragmentScreen> = listOf(
        FragmentScreen(
            articeTitle = R.string.title_1,
            textRes = R.string.screen_1,
            image = R.drawable.image_first,
            tag = ArticleTag.LISENCE,
            isShown = true
        ),
        FragmentScreen(
            articeTitle = R.string.title_2,
            textRes = R.string.screen_2,
            image = R.drawable.image_second,
            tag = ArticleTag.SIENCE,
            isShown = true
        ),
        FragmentScreen(
            articeTitle = R.string.title_3,
            textRes = R.string.screen_3,
            image = R.drawable.image_third,
            tag = ArticleTag.GEOGRAPHY,
            isShown = true
        ),
        FragmentScreen(
            articeTitle = R.string.title_4,
            textRes = R.string.screen_4,
            image = R.drawable.image_fourth,
            tag = ArticleTag.COLORS,
            isShown = true
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        filterScreens = screens.filter { it.isShown }
        setViewPager()
    }

    private fun setViewPager(){
        val adapter = ArticleAdapter(filterScreens, this)
        viewPager.adapter = adapter

        val springDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        TabLayoutMediator(tabLayout2, viewPager) { tab, position ->
            tab.setText(filterScreens[position].articeTitle)
        }.attach()

        springDotsIndicator.setViewPager2(viewPager)
        viewPager.setPageTransformer { page, position ->
            DepthTransformation().transformPage(page, position)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout2.getTabAt(position)?.removeBadge()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        onSaveInstanceState(Bundle())
    }

    fun initToolBar() {
        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
        }
        toolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_2 -> {
                    showAllert()
                    true
                }
                else -> false
            }
        }
    }

    private fun showAllert() {
        FilterDialogFragment( checkedItems, filterScreens).show(supportFragmentManager, "filterFragment")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PARC, state)
    }

    private fun toastShow(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}


enum class ArticleTag(var articleTag: String) {
    LISENCE("Договор"),
    SIENCE("Наука"),
    GEOGRAPHY("География"),
    COLORS("Цвета")
}
