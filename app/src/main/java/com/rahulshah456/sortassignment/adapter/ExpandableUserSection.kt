package com.rahulshah456.sortassignment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahulshah456.sortassignment.R
import com.rahulshah456.sortassignment.models.User
import com.rahulshah456.sortassignment.utils.Constant.SIMPLE_DATE_FORMAT
import com.rahulshah456.sortassignment.utils.Constant.STATUS_ACTIVE
import com.rahulshah456.sortassignment.utils.Constant.STATUS_LEFT
import com.rahulshah456.sortassignment.utils.Constant.STATUS_ONBOARDED
import com.rahulshah456.sortassignment.utils.DateUtils
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import java.lang.NullPointerException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ExpandableUserSection(private val context: Context,private val header: String, private val list: List<User>,
                            private val clickListener: ClickListener) :
        Section(SectionParameters.builder()
        .itemResourceId(R.layout.rv_item)
        .headerResourceId(R.layout.rv_header)
        .build()) {

    // boolean methods for state checks
    var isExpanded = true
    override fun getContentItemsTotal(): Int {
        return if (isExpanded) list.size else 0
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return UserViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // init
        val userHolder = holder as UserViewHolder
        val (dateString, img, gender, name, id, age, status) = list[position]

        // setting general parameters
        userHolder.name.text = name
        userHolder.sex.setText(if (gender == "m") R.string.male else R.string.female)
        userHolder.age.text = age.toString()
        userHolder.status.text = status


        // creating material status card
        userHolder.statusHeader.setImageResource(getStatusDrawable(status!!))
        val colorLight = getLightStatusColor(status)
        val colorPrimary = getPrimaryStatusColor(status)
        userHolder.statusBackground.setBackgroundColor(context.resources.getColor(colorLight))
        userHolder.status.setTextColor(context.resources.getColor(colorPrimary))
        userHolder.statusHeader.setColorFilter(ContextCompat.getColor(context, colorPrimary),
                android.graphics.PorterDuff.Mode.SRC_IN)


        // setting profile pic
        Glide.with(userHolder.profilePic.context)
                .load(img)
                .placeholder(R.drawable.avatar_24dp)
                .dontAnimate()
                .into(userHolder.profilePic)


        // setting date for status change
        try {
            val calendar: Calendar = GregorianCalendar()
            val date = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.ENGLISH).parse(dateString!!)
            calendar.time = date!!
            userHolder.position.text = DateUtils.getDateValue(calendar)

        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NullPointerException){
            e.printStackTrace()
        }
    }


    private fun getStatusDrawable(status: String) :Int = when(status) {
        STATUS_ACTIVE -> R.drawable.status_active_24dp
        STATUS_ONBOARDED -> R.drawable.status_onboarded_24dp
        STATUS_LEFT -> R.drawable.status_left
        else -> R.drawable.status_left
    }

    private fun getPrimaryStatusColor(status: String) :Int = when (status){
        STATUS_ACTIVE -> R.color.status_color_active
        STATUS_ONBOARDED -> R.color.status_color_onboarded
        STATUS_LEFT -> R.color.status_color_left
        else -> R.color.status_color_left
    }


    private fun getLightStatusColor(status: String) :Int = when (status){
        STATUS_ACTIVE -> R.color.status_background_active
        STATUS_ONBOARDED -> R.color.status_background_onboarded
        STATUS_LEFT -> R.color.status_background_left
        else -> R.color.status_background_left
    }


    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder) {
        //init
        val headerHolder = holder as HeaderViewHolder

        // setting parameters
        headerHolder.tvTitle.text = header
        headerHolder.ivArrow.setImageResource(
                if (isExpanded) R.drawable.collapse_24dp else R.drawable.expand_24dp
        )

        // init listener
        headerHolder.itemView.setOnClickListener {
            clickListener.onHeaderRootViewClicked(this@ExpandableUserSection)
        }
    }

    // custom interface of clicks
    interface ClickListener {
        fun onHeaderRootViewClicked(section: ExpandableUserSection)
        fun onItemRootViewClicked(section: ExpandableUserSection, itemAdapterPosition: Int)
    }

}