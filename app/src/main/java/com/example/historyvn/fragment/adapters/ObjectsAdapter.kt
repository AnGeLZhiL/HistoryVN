package com.example.historyvn.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.historyvn.R
import com.example.models.ObjectModel
import com.squareup.picasso.Picasso

class ObjectsAdapter(private val objects: List<ObjectModel>): RecyclerView.Adapter<ObjectsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val location: TextView = itemView.findViewById(R.id.location)
        val information: TextView = itemView.findViewById(R.id.information)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ObjectsAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.object_item, parent, false)
        return ObjectsAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ObjectsAdapter.MyViewHolder, position: Int) {
        holder.location.text = objects[position].location
        holder.information.text = objects[position].title
        objects[position].images.firstOrNull()?.let { Picasso.get().load(it.url).into(holder.image) }

    }

    override fun getItemCount(): Int {
        return objects.size
    }

}