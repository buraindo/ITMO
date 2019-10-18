package ru.ifmo.ibragimov.networking

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image.view.*

class ImageViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
    val image: TextView = root.image
}
