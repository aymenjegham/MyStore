package com.angelstudios.core.domain.product


import java.io.Serializable

data class Product(

    val id: String,

    val name: String,

    val price: Float,

    val currency: String,

    val companyId: Float,


    ) : Serializable
