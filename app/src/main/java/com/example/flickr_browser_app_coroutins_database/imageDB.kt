package com.example.flickr_browser_app_coroutins_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [image::class],version = 1,exportSchema = false)
abstract class imageDB:RoomDatabase() {
    abstract fun MyImgDao(): imageDAO;
    companion object{
        var instance:imageDB?=null;
        fun getInstance(ctx: Context):imageDB{
            if(instance!=null)
            {
                return  instance as imageDB;
            }
            instance = Room.databaseBuilder(ctx,imageDB::class.java,"raam11").run { allowMainThreadQueries() }.build()
            return instance as imageDB;
        }
    }
}