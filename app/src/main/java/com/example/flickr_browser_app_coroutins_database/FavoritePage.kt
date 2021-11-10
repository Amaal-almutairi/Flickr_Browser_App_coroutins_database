package com.example.flickr_browser_app_coroutins_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_favorite_page.*

class FavoritePage : AppCompatActivity() {
    lateinit var rvdb:RecyclerView
    lateinit var images:ArrayList<image>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_page)
        rvdb=findViewById(R.id.rvdb)
        images = arrayListOf()
        rvdb.adapter = RVAdap(this,images)
        rvdb.layoutManager= LinearLayoutManager(this)

        val api = imageDB.getInstance(this).MyImgDao().getallImage()
        for (i in api){
            images.add(i)
        }
        img4.setOnClickListener {

        }
    }
    fun display(image:String){
        Glide.with(this).load(image).into(img4)
        rvdb.visibility=View.GONE
        img4.visibility = View.VISIBLE
    }

    fun close(){
        rvdb.visibility = View.VISIBLE
        img4.visibility = View.GONE
    }
}