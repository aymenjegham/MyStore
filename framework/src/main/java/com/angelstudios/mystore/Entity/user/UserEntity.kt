package com.angelstudios.mystore.Entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable


@Entity(tableName ="customer")
data class UserEntity(


    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String,

    ) : Serializable
