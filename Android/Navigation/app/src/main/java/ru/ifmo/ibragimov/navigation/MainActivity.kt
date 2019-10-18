package ru.ifmo.ibragimov.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var current: String? = null
    private var tabTransitions: Deque<String> = ArrayDeque()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            changeTab(R.id.home_button)
        } else {
            current = savedInstanceState.getString(CURRENT_FRAGMENT_TAG)
            title = titleById[current!!.toInt()]
            tabTransitions = ArrayDeque(savedInstanceState.getStringArrayList(TAB_TRANSITIONS)!!)
            side_menu?.setCheckedItem(current!!.toInt())
            navigation?.selectedItemId = current!!.toInt()
        }

        navigation?.setOnNavigationItemSelectedListener {
            changeTab(it.itemId)
            true
        }

        side_menu?.setNavigationItemSelectedListener {
            changeTab(it.itemId)
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(CURRENT_FRAGMENT_TAG, current)
        outState.putStringArrayList(TAB_TRANSITIONS, ArrayList(tabTransitions))
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(current)
        when {
            fragment != null && fragment.childFragmentManager.backStackEntryCount > 0 -> fragment.childFragmentManager.popBackStack()
            supportFragmentManager.backStackEntryCount > 1 -> {
                tabTransitions.pop()
                current = tabTransitions.peek() ?: R.id.home_button.toString()
                title = titleById[current!!.toInt()]
                supportFragmentManager.popBackStack()
            }
            else -> finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeTab(id: Int): Boolean {
        val tag = id.toString()
        val tabContainer =
            supportFragmentManager.findFragmentByTag(tag) ?: ContainerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(current)
            .also { if (it != null) transaction.hide(it) }
        if (tabContainer.isAdded) {
            transaction.show(tabContainer)
        } else {
            transaction.add(R.id.main_activity, tabContainer, tag)
        }
        if (tag != current) {
            transaction.addToBackStack(null)
            tabTransitions.push(tag)
        }
        transaction.commit()
        current = tabContainer.tag
        title = titleById[id]
        return true
    }

    companion object {
        private const val CURRENT_FRAGMENT_TAG = "tag"
        private const val TAB_TRANSITIONS = "tabTransitions"
        private val titleById = mapOf(
            Pair(R.id.home_button, "Home"),
            Pair(R.id.dashboard, "Dashboard"),
            Pair(R.id.notifications, "Notifications")
        )
    }
}
