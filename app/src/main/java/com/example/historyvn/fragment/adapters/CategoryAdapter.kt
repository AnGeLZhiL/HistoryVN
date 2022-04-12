package com.example.historyvn.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.historyvn.R
import com.example.models.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categories: List<Pair<CategoryModel, () -> Unit>>):
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text_categories)
        val image: ImageView = itemView.findViewById(R.id.img_categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.categories_item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = categories[position].first.title
        holder.text.setOnClickListener { categories[position].second() }
        Picasso.get().load(categories[position].first.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}