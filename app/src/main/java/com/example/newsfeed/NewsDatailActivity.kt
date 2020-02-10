package com.example.newsfeed

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class NewsDatailActivity : AppCompatActivity() {

    private var detailThumbnailView : ImageView? = null

    private var detailTitleView : TextView? = null

    private var authorView : TextView? = null

    private var publishedAtView : TextView? = null

    private var descView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_item_detail)

        detailThumbnailView = findViewById(R.id.detailThumbnailView)

        detailTitleView = findViewById(R.id.detailTitleView)

        authorView = findViewById(R.id.authorView)

        publishedAtView = findViewById(R.id.publishedAtView)

        descView = findViewById(R.id.detailDescView)

        var bundle = intent.getExtras() as Bundle

        var article = bundle.getSerializable("article") as Article

        Glide.with(this).load(article.urlToImage).into(detailThumbnailView!!)

        detailTitleView?.text = article.title

        authorView?.text = article.author

        publishedAtView?.text = article.publishedAt

        descView?.text = article.description

    }
}