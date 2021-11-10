package com.example.flickr_browser_app_coroutins_database

import androidx.room.*

@Dao
interface  imageDAO {
    @Query("SELECT * FROM photos ORDER BY id ASC")
    fun getallImage():List<image>

    @Insert
    fun insert(img:image)

    @Delete
    fun Delete(img:image)

    @Update
    fun Update(img:image)

}