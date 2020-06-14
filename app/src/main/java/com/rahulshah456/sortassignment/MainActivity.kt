package com.rahulshah456.sortassignment

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.sortassignment.adapter.ExpandableUserSection
import com.rahulshah456.sortassignment.adapter.ExpandableUserSection.ClickListener
import com.rahulshah456.sortassignment.models.User
import com.rahulshah456.sortassignment.viewmodels.UserViewModel
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter


class MainActivity : AppCompatActivity(), ClickListener {

    private var sectionedAdapter: SectionedRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //init views
        initStatusBarColor()
        initRecyclerView()

        //Subscribe to ViewModel Wallpapers
        subscribeUi(viewModel.mutableUsers)
    }

    private fun subscribeUi(liveData: MutableLiveData<List<User?>?>) {
        // Update the list when the data changes
        liveData.observe(this, Observer { users ->
            if (users!= null) {
                generateSortedList(users as List<User>)
            }
        })
    }


    // main assignment function for sorted list
    private fun generateSortedList(users: List<User>){
        val distinctUsers =  users.distinctBy { it.status }.sortedBy { it.status }
        for (user in distinctUsers){
            val header  = user.status
            val sortedList = users.filter{it.status == user.status}.sortedBy { it.getFormattedDate() }
            sectionedAdapter!!.addSection(ExpandableUserSection(this@MainActivity, header!!,
                    sortedList, this@MainActivity))
            sectionedAdapter!!.notifyDataSetChanged()
        }
    }



    private fun initStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)
    }
    private fun initRecyclerView() {
        sectionedAdapter = SectionedRecyclerViewAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_sorted)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = sectionedAdapter
    }



    override fun onHeaderRootViewClicked(section: ExpandableUserSection) {
        val sectionAdapter = sectionedAdapter!!.getAdapterForSection(section)

        // store info of current section state before changing its state
        val wasExpanded = section.isExpanded
        val previousItemsTotal = section.contentItemsTotal
        section.isExpanded = !wasExpanded
        sectionAdapter.notifyHeaderChanged()
        if (wasExpanded) {
            sectionAdapter.notifyItemRangeRemoved(0, previousItemsTotal)
        } else {
            sectionAdapter.notifyAllItemsInserted()
        }
    }
    override fun onItemRootViewClicked(section: ExpandableUserSection, itemAdapterPosition: Int) {
        Toast.makeText(this, "Clicked $itemAdapterPosition", Toast.LENGTH_SHORT).show()
    }
}