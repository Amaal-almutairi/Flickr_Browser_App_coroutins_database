package com.example.flickr_browser_app_coroutins_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photos")
data class image (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name ="id") var id:Int?,
    @ColumnInfo(name = "img") val img :String,
    @ColumnInfo(name = "author") val author:String,
    @ColumnInfo(name = "ischecked") var ischecked:Boolean = false
        )
