package com.rahulshah456.sortassignment.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.sortassignment.R
import de.hdodenhof.circleimageview.CircleImageView

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val profilePic: CircleImageView = itemView.findViewById(R.id.civ_profile_pic)
    val name: TextView = itemView.findViewById(R.id.tv_name)
    val position: TextView = itemView.findViewById(R.id.tv_work)
    val status: TextView = itemView.findViewById(R.id.tv_status)
    val age: TextView = itemView.findViewById(R.id.tv_age)
    val sex: TextView = itemView.findViewById(R.id.tv_sex)
    val statusHeader: ImageView = itemView.findViewById(R.id.iv_status)
    val statusBackground: LinearLayout = itemView.findViewById(R.id.ll_status)
}