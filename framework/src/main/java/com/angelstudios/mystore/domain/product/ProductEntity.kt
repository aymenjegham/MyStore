package com.angelstudios.mystore.domain.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable


@Entity(tableName ="product")
data class ProductEntity(


    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,


    @ColumnInfo(name = "price")
    @Expose
    @SerializedName("price")
    val price: Float,

    @ColumnInfo(name = "currency")
    @Expose
    @SerializedName("currency")
    val currency: String,

    @ColumnInfo(name = "companyId")
    @Expose
    @SerializedName("companyId")
    val companyId: Float,

    ) : Serializable
