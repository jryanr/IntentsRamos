package com.example.intentsramos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(Intent.ACTION_VIEW)

        intent.setAction(Intent.ACTION_ANSWER)
        var uri: Uri.parse(uiString
    }
}