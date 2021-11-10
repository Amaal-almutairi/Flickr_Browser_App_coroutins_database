package com.example.flickr_browser_app_coroutins_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.coroutines.*
import org.json.JSONArray
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var images:ArrayList<image>
    var url=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv)
        images = arrayListOf()
        url ="https://picsum.photos/v2/list"
        getAPI()
        imageView.setOnClickListener {
            closeImg()
        }
        rv.adapter=RVAdap(this,images)
        rv.layoutManager=LinearLayoutManager(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.fav-> startActivity(Intent(this,FavoritePage::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    fun getAPI(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = async { CheckURL() }.await()
            if (data.isNotEmpty()){
                bindingToView(data)
                }
            }
        }

    fun CheckURL():String{
        var url =""
        try {
            url = URL(this.url).readText(Charsets.UTF_8)
        }catch (e:Exception){
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()

        }
        return url
    }

    suspend fun bindingToView(data:String){
        withContext(Dispatchers.Main){
            val jsonObj=JSONArray(data)
            for (i in 0 until jsonObj.length()){
                val title = jsonObj.getJSONObject(i).getString("author").toString()
                val img = jsonObj.getJSONObject(i).getString("download_url").toString()
                images.add(image(null,img,title,false))
            }
            rv.adapter?.notifyDataSetChanged()
        }
    }

    fun display(image:String){
        Glide.with(this).load(image).into(imgv2)
        rv.visibility = View.VISIBLE

    }

    fun  closeImg(){
        rv.visibility=View.GONE
        imgv2.visibility=View.GONE
    }


}
