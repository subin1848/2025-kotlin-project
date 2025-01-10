package com.example.studytimerproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // 유저 정보 표시
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        // 우선 email -> 이후 nickname으로 변경
        Toast.makeText(this, "환영합니다. $email 님!", Toast.LENGTH_SHORT).show()
    }
}