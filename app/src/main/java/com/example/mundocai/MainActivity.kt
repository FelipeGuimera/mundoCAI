package com.example.mundocai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import com.example.mundocai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        supportActionBar?.setCustomView(R.layout.toolbar)



        setContentView(R.layout.activity_main)
    }
}