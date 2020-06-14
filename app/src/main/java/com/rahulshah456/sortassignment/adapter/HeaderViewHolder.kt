package com.rahulshah456.sortassignment.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.sortassignment.R

class HeaderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    val ivArrow: ImageView = itemView.findViewById(R.id.ivArrow)
}