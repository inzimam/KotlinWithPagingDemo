package com.inzy.kotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_footer.view.*

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(state: State?) {
        itemView.progress_bar.visibility = if (state == State.LOADING) VISIBLE else INVISIBLE
        itemView.txt_error.visibility = if (state == State.ERROR) VISIBLE else INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): FooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return FooterViewHolder(view)
        }
    }
}