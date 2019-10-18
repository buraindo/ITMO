package ru.ifmo.ibragimov.networking

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(
    private val images: List<Image>,
    val onClick: (Image) -> Unit
) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.image.text = images[position].description
        holder.image.setCompoundDrawablesWithIntrinsicBounds(
            BitmapDrawable(
                holder.image.context.resources,
                BitmapFactory.decodeStream(holder.image.context.openFileInput(images[position].thumb))
            ),
            null,
            null,
            null
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val holder = ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image,
                parent,
                false
            )
        )
        holder.root.setOnClickListener {
            onClick(images[holder.adapterPosition])
        }
        return holder
    }

}