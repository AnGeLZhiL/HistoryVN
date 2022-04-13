package com.example.historyvn.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.historyvn.R
import com.example.historyvn.models.TestModelWithResult

class ResultsAdapter(private val objects: List<TestModelWithResult>) : RecyclerView.Adapter<ResultsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.question_title)
        val score: TextView = itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.test_item_with_result, parent, false)
        return ResultsAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = objects[position].title
        holder.score.text = objects[position].current.toString()
    }

    override fun getItemCount(): Int = objects.size

}