package com.inzy.kotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_feeds.view.*

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(feed: Feed?) {
        if (feed != null) {
            itemView.txt_feed_name.text = feed.title
            Picasso.get().load(feed.image).into(itemView.img_feed_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): FeedViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feeds, parent, false)
            return FeedViewHolder(view)
        }
    }
}