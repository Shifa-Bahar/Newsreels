package com.example.myapplication.model.sports

import com.google.gson.annotations.SerializedName
data class StagesBase (
	@SerializedName("Stages") val stages : ArrayList<Stages>? = ArrayList()
)