package com.gsrikar.reqressample.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data object in the [ReqresResponse]
 */
@Entity(tableName = "reqres")
data class ReqresData(

    /**
     * Unique id given to the user
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    /**
     * Email address of the user
     */
    @ColumnInfo(name = "email")
    val email: String,

    /**
     * First name of the user
     */
    @ColumnInfo(name = "first_name")
    val first_name: String,

    /**
     * Last name of the user
     */
    @ColumnInfo(name = "last_name")
    val last_name: String,

    /**
     * Image url of the user
     */
    @ColumnInfo(name = "avatar")
    val avatar: String
)