package com.angelstudios.core.domain.customer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Customer(

    @Expose @SerializedName("id") val id: String,

    @Expose @SerializedName("name") val name: String,

    ) : Serializable
