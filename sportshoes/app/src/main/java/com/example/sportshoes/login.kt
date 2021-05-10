package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_login.*
import javax.security.auth.login.LoginException

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun loginMasuk(view: View) {
        val Uname   = edUsername.text.toString()
        val Upass   = edPassword.text.toString()

        if (Uname.isEmpty() || Upass.isEmpty()){
            Toast.makeText(this, "Isikan Username atau Password dulu !", Toast.LENGTH_SHORT).show()
        }else if (Uname.toLowerCase() == "shefa" && Upass.toLowerCase() == "123456"){
            Toast.makeText(this,"Login Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }else{
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}