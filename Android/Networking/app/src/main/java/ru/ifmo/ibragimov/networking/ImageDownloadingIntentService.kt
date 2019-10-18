package ru.ifmo.ibragimov.networking

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

private var page = 1
private const val perPage = 10
private const val maxCacheSize = 100

private val cache = LinkedHashMap<String, String>()
private val uris = ArrayDeque<String>()

private lateinit var previousArray: ArrayList<Parcelable>
private lateinit var previous: String

@Parcelize
data class Image(
    val id: String,
    val image: String,
    val thumb: String,
    val description: String,
    val link: String
) :
    Parcelable

class ImageDownloadingIntentService(name: String = "") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        val response = Intent(getString(R.string.action))
        when (intent?.getStringExtra("type")) {
            "page" -> {
                response.putParcelableArrayListExtra("result", downloadImages())
            }
            "one" -> {
                response.putExtra(
                    "result",
                    downloadImage(
                        intent.getStringExtra("id"),
                        intent.getStringExtra("link")
                    )
                )
            }
            "loadedImage" -> {
                response.putExtra("result", previous)
            }
            "loadedPage" -> {
                response.putParcelableArrayListExtra("result", previousArray)
            }
        }
        response.putExtra("type", intent?.getStringExtra("type"))
        LocalBroadcastManager.getInstance(this).sendBroadcast(response)
    }

    private fun downloadImages(): ArrayList<out Parcelable>? {
        val connection = URL(
            "${getString(R.string.url)}?page=$page&per_page=$perPage&client_id=${getString(R.string.id)}"
        ).openConnection() as HttpURLConnection
        val images = JSONArray(connection.inputStream.reader().readText())
        page++
        val result = ArrayList<Parcelable>()
        for (i in 0 until images.length()) {
            val image = images.getJSONObject(i)
            val urls = image.getJSONObject("urls")
            val thumb = URL(urls.getString("thumb")).readBytes()
            val description = image.getString("description")
            val altDescription = image.getString("alt_description")
            val bitmap = BitmapFactory.decodeByteArray(thumb, 0, thumb.size)
            val resultDescription = if (description == "null") {
                if (altDescription == "null") {
                    getString(R.string.no_description)
                } else altDescription
            } else description
            val id = image.getString("id")
            result.add(
                Image(
                    id,
                    id,
                    saveImage("thumb$id", bitmap),
                    resultDescription,
                    urls.getString(getString(R.string.mode))
                )
            )
        }
        previousArray = result
        return result
    }

    private fun downloadImage(id: String?, link: String?): String? {
        if (cache.containsKey(id)) return cache[id]
        val bytes = URL(link).readBytes()
        val result = saveImage(id, BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
        cache[id.toString()] = result
        uris.addLast(id)
        if (cache.size > maxCacheSize) {
            cache.remove(uris.removeFirst())
        }
        previous = result
        return result
    }

    private fun saveImage(id: String?, bitmap: Bitmap): String {
        return try {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val output = openFileOutput(id, Context.MODE_PRIVATE)
            output.write(bytes.toByteArray())
            output.close()
            id.toString()
        } catch (e: IOException) {
            ""
        }
    }

}