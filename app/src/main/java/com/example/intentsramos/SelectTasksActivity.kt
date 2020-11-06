package com.example.intentsramos

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class SelectTasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tasks)


        findViewById<Button>(R.id.canvas_btn).setOnClickListener{openWebPage("https://uc-bcf.instructure.com/")}
        findViewById<Button>(R.id.cam_btn).setOnClickListener{dispatchTakePictureIntent()}
        findViewById<Button>(R.id.wifi_settings_btn).setOnClickListener{openWifiSettings()}
        findViewById<Button>(R.id.alarm_btn).setOnClickListener{createAlarm("Wake up", 6, 0)}
        findViewById<Button>(R.id.open_btn).setOnClickListener{openFile()}
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    fun openWifiSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        try {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minutes)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }catch (e: Exception){
            val msg = "The intent failed because this application does not have permission to open clock application."
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }

    fun openFile (){
        val file = File(Environment.getExternalStorageDirectory(),
                "Report.pdf")
        val path = Uri.fromFile(file)
        val pdfOpenintent = Intent(Intent.ACTION_VIEW)
        pdfOpenintent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        pdfOpenintent.setDataAndType(path, "application/pdf")
        try {
            startActivity(pdfOpenintent)
        } catch (e: Exception) {
            val msg = "The intent failed because file does not exist."
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }

}