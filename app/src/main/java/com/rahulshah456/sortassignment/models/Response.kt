package com.rahulshah456.sortassignment.models

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("list")
	val list: List<User?>? = null
)