package com.angelstudios.core.domain.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(

    @Expose @SerializedName("id") val id: String,


    @Expose @SerializedName("name") val name: String,

    @Expose @SerializedName("price") val price: Float,

    @Expose @SerializedName("currency") val currency: String,

    @Expose @SerializedName("companyId") val companyId: Float,


    ) : Serializable
