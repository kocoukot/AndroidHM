package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var state: FormState = FormState(valid = true, message = "")
    private val KEY_PARC = "ParcKey"
    private val KEY_BOOLS = "BoolKeys"
    private val KEY_BADGES = "Badges"

    private var checkedItems = booleanArrayOf(true, true, true, true)
    private var badges = mutableListOf<Int>()

    private val screens: List<FragmentScreen> = listOf(
        FragmentScreen(
            articeTitle = R.string.title_1,
            textRes = R.string.screen_1,
            image = R.drawable.image_first,
            tag = ArticleTag.LISENCE,
            badgeAmount = 0
        ),
        FragmentScreen(
            articeTitle = R.string.title_2,
            textRes = R.string.screen_2,
            image = R.drawable.image_second,
            tag = ArticleTag.SIENCE,
            badgeAmount = 0
        ),
        FragmentScreen(
            articeTitle = R.string.title_3,
            textRes = R.string.screen_3,
            image = R.drawable.image_third,
            tag = ArticleTag.GEOGRAPHY,
            badgeAmount = 0
        ),
        FragmentScreen(
            articeTitle = R.string.title_4,
            textRes = R.string.screen_4,
            image = R.drawable.image_fourth,
            tag = ArticleTag.COLORS,
            badgeAmount = 0
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null ){
            checkedItems = savedInstanceState.getBooleanArray(KEY_BOOLS)!!
            badges = savedInstanceState.getIntArray(KEY_BADGES)!!.toMutableList()
            for (s in 0 until screens.count()){
                screens[s].badgeAmount = badges[s]
            }
        }
        initToolBar()
        onAcceptFilter(checkedItems)
    }

    private fun setViewPager(screen: List<FragmentScreen>) {
        val adapter = ArticleAdapter(screen, this)
        viewPager.adapter = adapter

        val springDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        TabLayoutMediator(tabLayout2, viewPager) { tab, position ->
            tab.setText(screen[position].articeTitle)
        }.attach()

        for (s in 0 until screen.count()) {
            if (screen[s].badgeAmount != 0) {
                tabLayout2.getTabAt(s)?.orCreateBadge?.apply {

                    number = screen[s].badgeAmount
                    badgeGravity = BadgeDrawable.TOP_END
                }
            }
        }

        springDotsIndicator.setViewPager2(viewPager)
        viewPager.setPageTransformer { page, position ->
            DepthTransformation().transformPage(page, position)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout2.getTabAt(position)?.removeBadge()
                screens[position].badgeAmount = 0
            }
        })
    }

    fun onAcceptFilter(bools: BooleanArray) {
        val tagsToShow = mutableListOf<ArticleTag>()
        val filtered: MutableList<FragmentScreen> = mutableListOf()
        checkedItems = bools
        for (b in 0 until bools.count()) {
            if (bools[b]) {
                tagsToShow.add(ArticleTag.values()[b])
            }
        }

        for (i in 0 until tagsToShow.count()) {
            for (s in 0 until screens.count()) {
                if (screens[s].tag == tagsToShow[i]) {
                    filtered.add(screens[s])
                }
            }
        }
        setViewPager(filtered)
    }

    override fun onPause() {
        super.onPause()
        onSaveInstanceState(Bundle())
    }

    private fun initToolBar() {
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
        FilterDialogFragment(checkedItems).show(supportFragmentManager, "filterFragment")
    }

    fun setBadge(index: Int, badge: Int){
        screens[index].badgeAmount = badge
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        for (i in 0 until screens.count()){
            badges.add(screens[i].badgeAmount)
        }
        outState.putParcelable(KEY_PARC, state)
        outState.putBooleanArray(KEY_BOOLS,checkedItems)
        outState.putIntArray(KEY_BADGES,badges.toIntArray())

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
