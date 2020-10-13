package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagenetwork.R
import kotlinx.android.synthetic.main.list_item.view.*
import net.kodehawa.lib.imageboards.entities.BoardImage

class ImageAdapter(
    private val images: List<BoardImage>,
    private val onClick: (BoardImage) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ContactViewHolder>() {
    class ContactViewHolder(var root: View) : RecyclerView.ViewHolder(root) {
        fun bind(image: BoardImage) {
            with(root) {
                description.text = image.tags[0]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ContactViewHolder {
        val holder = ContactViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
        holder.root.setOnClickListener {
            onClick(images[holder.adapterPosition])
        }
        return holder
    }


    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) = holder.bind(images[position])


    override fun getItemCount(): Int = images.size
}