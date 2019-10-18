package ru.ifmo.ibragimov.contactlist

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val uniqueKey = 228

    private fun load() {
        val contacts = fetchAllContacts()
        val viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = ContactAdapter(contacts) {
                val intent = Intent(ACTION_DIAL).apply {
                    data = Uri.parse("tel:${it.phoneNumber}")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
        Toast.makeText(
            this@MainActivity,
            resources.getQuantityString(R.plurals.ok, contacts.size, contacts.size),
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_CONTACTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                uniqueKey
            )
        } else {
            load()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            uniqueKey -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    load()
                } else {
                    switcher.showNext()
                }
                return
            }
        }
    }

}
