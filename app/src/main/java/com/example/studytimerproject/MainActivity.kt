package com.example.studytimerproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        // firestore db 연결
        val db = Firebase.firestore

        // firebase auth 사용자 UID
        val userId = auth.currentUser?.uid

        // 1번째 방법
        var email = findViewById<EditText>(R.id.emailArea)
        var pw = findViewById<EditText>(R.id.pwArea)
        var nickName = findViewById<EditText>(R.id.nickNameArea)
        var joinBtnClicked = findViewById<Button>(R.id.joinBtn)
        var login = findViewById<Button>(R.id.loginBtn)
        var logout = findViewById<Button>(R.id.logoutBtn)

        // 회원가입 클릭 리스너
        joinBtnClicked.setOnClickListener {
            // 회원가입 텍스트
            val emailText = email.text.toString()
            val pwText = pw.text.toString()
            val nickNameText = nickName.text.toString()

            // 필드 누락된 부분이 있으면
            if (emailText.isEmpty() || pwText.isEmpty() || nickNameText.isEmpty()) {
                Toast.makeText(this, "모두 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(emailText, pwText)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid

                        if (userId != null) {
                            // user 컬렉션 생성
                            var user = hashMapOf(
                                "email" to emailText,
//                                "password" to pwText,
                                "nickName" to nickNameText,
                            )

                            db.collection("users")
                                .document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "firestore 저장 실패..", Toast.LENGTH_SHORT).show()
                                }
                        }
                    } else {
                        // Firebase auth 회원가입 실패
                        Toast.makeText(this, "회원가입 실패..", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        //login
        login.setOnClickListener {
            val emailText = email.text.toString()
            val pwText = pw.text.toString()

            if (emailText.isEmpty() || pwText.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(emailText, pwText)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        // 로그인 성공 시 다른 액티비티로 이동
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()    // 현재 액티비티 종료 (뒤로 가기 방지)
                    } else {
                        Toast.makeText(this, "로그인 실패..", Toast.LENGTH_SHORT).show()
                        Toast.makeText(
                            this,
                            auth.currentUser?.uid.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        //logout
        logout.setOnClickListener {
//            val email = findViewById<EditText>(R.id.emailArea)
//            val pw = findViewById<EditText>(R.id.pwArea)
            email.setText("")
            pw.setText("")
            auth.signOut()
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
        }

    }
}