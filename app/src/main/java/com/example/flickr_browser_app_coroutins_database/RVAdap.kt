package com.example.flickr_browser_app_coroutins_database

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdap(val activity: Activity, var images:ArrayList<image>):RecyclerView.Adapter<RVAdap.ItemViewHolder>(){
    var TAG = "MainActivity"
    class ItemViewHolder (ItemView:View):RecyclerView.ViewHolder(ItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,parent,false
            ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var image = images[position]

        holder.itemView.apply {
            textView.text = image.author
            Glide.with(activity).load(image.img).into(imgv2)
            if (image.ischecked == true)
                imgv3.setColorFilter(Color.argb(255, 255, 50, 50))

            if (activity is MainActivity) {
                linrv.setOnClickListener {
                     activity.display(image.img) }
                }


            if (activity is FavoritePage) {
                //  linrv.setOnClickListener { activity.display(image.img) }
            }
        imgv3.setOnClickListener {
                if (!image.ischecked){
                    image.ischecked = true
                    imageDB.getInstance(activity).MyImgDao().insert(image)
                    imageDB.getInstance(activity).MyImgDao().Update(image)
              //   imgv3.setColorFilter(Color.parseColor(C))
                    notifyDataSetChanged()
                }
            }

        }
    }
        override fun getItemCount(): Int = images.size
    }




