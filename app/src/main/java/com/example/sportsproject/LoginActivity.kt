package com.example.sportsproject

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var auth : FirebaseAuth
    lateinit var newBtn : Button
    lateinit var createBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        emailEt = findViewById(R.id.email_et)
        passwordEt = findViewById(R.id.pwd_et)
        loginBtn = findViewById(R.id.btnLogin)
        createBtn = findViewById(R.id.btnCreate)

        loginBtn.setOnClickListener {
            if (emailEt.text.toString().isEmpty() || passwordEt.text.toString().isEmpty()){
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(emailEt.text.toString(), passwordEt.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            if(auth.currentUser!=null){
                                var intent = Intent(this,MainActivity::class.java)
                                startActivity(intent)
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "로그인 실패",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }

        createBtn.setOnClickListener {
            var intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateUI(cUser : FirebaseUser? = null){
        if(cUser != null){
            loginBtn.isEnabled = false
            createBtn.isEnabled = false
        }
        emailEt.setText("")
        passwordEt.setText("")
        hideKeyboard(emailEt)
    }


    @SuppressLint("ServiceCast")
    private fun hideKeyboard(view : View){
        view?.apply {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}