package com.example.sportsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var createbtn : Button
    lateinit var cancelbtn : Button

    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val TAG : String = "CreateAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference



        emailEt = findViewById(R.id.email_et)
        passwordEt = findViewById(R.id.pwd_et)

        createbtn = findViewById(R.id.btnCreate)
        cancelbtn = findViewById(R.id.btnCancel)



        createbtn.setOnClickListener {
            if(emailEt.text.toString().isEmpty() || passwordEt.text.toString().isEmpty()){
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(emailEt.text.toString(),passwordEt.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Log.d(TAG, "createUserWithEmail:success")
                            finish()
                        }else{
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            emailEt?.setText("")
                            passwordEt?.setText("")
                            emailEt.requestFocus()
                        }
                    }
            }

        }

        cancelbtn.setOnClickListener {
            finish()

        }





    }
}