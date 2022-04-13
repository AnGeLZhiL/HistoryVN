package com.example.historyvn.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.historyvn.R
import com.example.historyvn.models.TestModel

class TestsAdapter(private val objects: List<Pair<TestModel, () -> Unit >>): RecyclerView.Adapter<TestsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.test_item, parent, false)
        return TestsAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = objects[position].first.title
        holder.title.setOnClickListener { objects[position].second() }
    }

    override fun getItemCount(): Int = objects.size

}