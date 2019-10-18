package ru.ifmo.ibragimov.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val clientId by lazy { getString(R.string.id) }
    private val images = mutableListOf<Image>()

    private lateinit var imageReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tweak()
        load()
    }

    override fun onStart() {
        setReceiver()
        super.onStart()
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(imageReceiver)
        super.onStop()
    }

    private fun tweak() {
        val settings =
            applicationContext.getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
                .edit()
        settings.putString(getString(R.string.clientId), clientId)
        settings.apply()
    }

    private fun load() {
        val viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = ImageAdapter(images) {
                val intent = Intent(this.context, ImageActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("link", it.link)
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (viewManager.findLastCompletelyVisibleItemPosition() == viewManager.itemCount - 1) {
                        downloadPage()
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
        downloadPage()
    }

    private fun downloadPage() {
        val intent = Intent().apply {
            setClass(this@MainActivity, ImageDownloadingIntentService::class.java)
            putExtra("type", "page")
        }
        startService(intent)
    }

    private fun setReceiver() {
        imageReceiver = ImageReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(getString(R.string.action))
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(imageReceiver, intentFilter)
        retrieveLastResult()
    }

    private fun retrieveLastResult() {
        val intent = Intent().apply {
            setClass(this@MainActivity, ImageDownloadingIntentService::class.java)
            putExtra("type", "loadedPage")
        }
        startService(intent)
    }

    private inner class ImageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val type = intent.getStringExtra("type")
            if (type == "page" || type == "loadedPage") {
                images += intent.getParcelableArrayListExtra("result")
                    ?: emptyList()
                recyclerView.adapter?.notifyDataSetChanged()
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

}
