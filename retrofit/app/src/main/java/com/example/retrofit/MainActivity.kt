package com.example.retrofit

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()
    }

    private fun getImages() {
        val apiKey = "47023660-1d7e8069e16f071e95ddc1e76"
        val query = "yellow flowers"
        val imageType = "photo"
        val pretty = true

        RetrofitClient.instance.getImages(apiKey, query, imageType, pretty)
            .enqueue(object : Callback<PixabayResponse> {
                override fun onResponse(
                    call: Call<PixabayResponse>,
                    response: Response<PixabayResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("DDD", "${response.body()}")
                        val images = response.body()?.hits?.map { it.previewURL } ?: emptyList()
                        setupRecyclerView(images)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setupRecyclerView(images: List<String>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ImageAdapter(images)
        recyclerView.setHasFixedSize(true)
    }
}