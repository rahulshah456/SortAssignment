package com.rahulshah456.sortassignment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rahulshah456.sortassignment.models.Response
import com.rahulshah456.sortassignment.models.User
import com.rahulshah456.sortassignment.retrofit.Service
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var userList: List<User?>?
    private val mutableUsersList: MutableLiveData<List<User?>?>

    init {
        userList = ArrayList()
        mutableUsersList = MutableLiveData()
    }

    private fun fetchUsers() {
        Service.getInstance().allUsers.enqueue(object : Callback<Response?> {
            override fun onResponse(call: Call<Response?>, response: retrofit2.Response<Response?>) {
                if (response.body() != null) {
                    userList = response.body()!!.list
                    mutableUsersList.value = userList
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    val mutableUsers: MutableLiveData<List<User?>?>
        get() {
            if (userList!!.isEmpty()) fetchUsers()
            return mutableUsersList
        }
}