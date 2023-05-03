package com.example.sportsproject.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sportsproject.utils.BoardModel
import com.example.sportsproject.R
import com.example.sportsproject.utils.FBAuth
import com.example.sportsproject.utils.FBRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {
    lateinit var insertBtn : Button
    lateinit var editTitle : EditText
    lateinit var editContent : EditText

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)
        val editTitle : EditText = findViewById(R.id.editTitle)
        val editContent : EditText = findViewById(R.id.editContent)
        val insertBtn : Button = findViewById(R.id.insertBtn)

        val database = Firebase.database
        val boardRef = database.getReference("board")
        auth = FirebaseAuth.getInstance()

        insertBtn.setOnClickListener {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            FBRef.boardRef
                .push()
                .setValue(BoardModel(title,content,uid,time))
            Toast.makeText(this,"게시글 입력 완료",Toast.LENGTH_LONG).show()
            finish()

        }
    }
}