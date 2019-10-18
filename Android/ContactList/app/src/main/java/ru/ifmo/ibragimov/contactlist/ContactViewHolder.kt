package ru.ifmo.ibragimov.contactlist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact.view.*

class ContactViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
    val name: TextView = root.name
    val phoneNumber: TextView = root.phoneNumber
}
