package com.example.newsfeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.SearchView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private var recyclerView : RecyclerView? = null

    private var searchView : SearchView? = null

    private var cardView : CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init() // init all class vars

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                var retrofit = RetrofitInstance.retrofitInstance

                var newsService = retrofit?.create(NewsApiService::class.java)

                newsService?.let {
                    var call : retrofit2.Call<NewsApiResponse> = it.fetchNews(query, API_KEY)

                    call.enqueue(object : Callback<NewsApiResponse> {
                        override fun onFailure(call: retrofit2.Call<NewsApiResponse>, t: Throwable) {
                            errorToast()
                        }

                        override fun onResponse(
                            call: retrofit2.Call<NewsApiResponse>,
                            response: Response<NewsApiResponse>
                        ) {
                            if (response.code() == 200) {
                                recyclerView!!.adapter = RecyclerViewAdaptor(response.body()!!.articles)
                            } else {
                                errorToast()
                            }
                        }

                    })
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun init() {

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView?.adapter = RecyclerViewAdaptor(listOf())

        searchView = findViewById(R.id.searchView)

        cardView = findViewById(R.id.cardView)


    }

    private fun errorToast() {
        Toast.makeText(
            this@MainActivity,
            "Code fatt gya!!",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        val API_KEY = "6d1a117aba67413fa70fb9b0721bf146"
    }
}
