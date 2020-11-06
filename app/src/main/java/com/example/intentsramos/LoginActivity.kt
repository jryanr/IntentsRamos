package com.example.intentsramos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username_et = findViewById<EditText>(R.id.username_et)
        val password_et = findViewById<EditText>(R.id.password_et)



        findViewById<Button>(R.id.login_btn).setOnClickListener{
            var status =
                if (username_et.text.toString().equals("Ryan") && password_et.text.toString().equals("password")) "Log In Successful"
                else "Login Failed"
            Toast.makeText(this,status,Toast.LENGTH_SHORT).show()
            if(status.equals("Log In Successful")){
                        goToSelectTasks()
                    }
        }

    }

    private fun goToSelectTasks(){
        val intent = Intent(this, SelectTasks::class.java)
        startActivity(intent)
    }

}