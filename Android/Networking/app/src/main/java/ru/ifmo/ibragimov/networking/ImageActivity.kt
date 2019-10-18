package ru.ifmo.ibragimov.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_image.progressBar
import kotlinx.android.synthetic.main.activity_image.image

class ImageActivity : AppCompatActivity() {

    private lateinit var imageReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        downloadOne(
            intent.getStringExtra("id") ?: getString(R.string.defaultId),
            intent.getStringExtra("link") ?: getString(R.string.defaultLink)
        )
    }

    override fun onStart() {
        setReceiver()
        super.onStart()
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(imageReceiver)
        super.onStop()
    }

    private fun setReceiver() {
        imageReceiver = ImageReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(getString(R.string.action))
        LocalBroadcastManager.getInstance(this).registerReceiver(imageReceiver, intentFilter)
        retrieveLastResult()
    }

    private fun retrieveLastResult() {
        val intent = Intent().apply {
            setClass(this@ImageActivity, ImageDownloadingIntentService::class.java)
            putExtra("type", "loadedImage")
        }
        startService(intent)
    }

    private fun downloadOne(id: String, link: String) {
        val intent = Intent().apply {
            setClass(this@ImageActivity, ImageDownloadingIntentService::class.java)
            putExtra("type", "one")
            putExtra("id", id)
            putExtra("link", link)
        }
        startService(intent)
    }

    private inner class ImageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val type = intent.getStringExtra("type")
            if (type == "one" || type == "loadedImage") {
                image.setImageBitmap(
                    BitmapFactory.decodeStream(
                        openFileInput(
                            intent.getStringExtra(
                                "result"
                            )
                        )
                    )
                )
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

}