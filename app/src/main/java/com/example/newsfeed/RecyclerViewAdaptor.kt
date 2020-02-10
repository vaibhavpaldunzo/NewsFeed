package com.example.newsfeed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdaptor(val articleList : List<Article>) : RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val thumbnailView = itemView.findViewById(R.id.thumbnailView) as ImageView

        val titleView = itemView.findViewById(R.id.titleView) as TextView

        val descView = itemView.findViewById(R.id.descView) as TextView

        fun inject(article : Article) {
            Glide.with(itemView.context).load(article.urlToImage).into(thumbnailView)

            titleView.text = article.title

            descView.text = article.description
        }

        fun setListener(article: Article) {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, NewsDatailActivity::class.java)

                val bundle = Bundle()

                bundle.putSerializable("article", article)

                intent.putExtras(bundle)

                itemView.context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.inject(articleList[position])

        holder.setListener(articleList[position])
    }

}