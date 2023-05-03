package com.example.sportsproject.board

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sportsproject.R
import com.google.firebase.database.*

class BoardInsideActivity : AppCompatActivity() {
    lateinit var titleArea :TextView
    lateinit var contentArea : TextView
    lateinit var timeArea : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)


        titleArea = findViewById(R.id.titleArea)
        contentArea = findViewById(R.id.contentArea)
        timeArea = findViewById(R.id.timeArea)


        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()


        titleArea.text = title
        contentArea.text = content
        timeArea.text = time




    }

}