package com.rahulshah456.sortassignment.models

import com.google.gson.annotations.SerializedName
import com.rahulshah456.sortassignment.utils.Constant
import java.text.SimpleDateFormat
import java.util.*

data class User(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("status")
	val status: String? = null

) {

	@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
	fun getFormattedDate() :Date {
		return SimpleDateFormat(Constant.SIMPLE_DATE_FORMAT, Locale.ENGLISH).parse(date)
	}

}