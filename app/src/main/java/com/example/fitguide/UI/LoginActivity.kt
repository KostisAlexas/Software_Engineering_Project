package com.example.fitguide.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitguide.R
import com.example.fitguide.domain.model.User
import com.example.fitguide.utils.UserSession

class LoginActivity : AppCompatActivity() {
    // Dummy user for demonstration
    private val dummyUser = User(
        id = 2L,
        username = "test",
        password = "test",
        firstName = "Test",
        lastName = "User",
        email = "test@example.com"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If already logged in, skip login
        if (UserSession.isLoggedIn(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val usernameEdit = findViewById<EditText>(R.id.editTextUsername)
        val passwordEdit = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val username = usernameEdit.text.toString()
            val password = passwordEdit.text.toString()
            if (username == dummyUser.username && password == dummyUser.password) {
                UserSession.login(this, dummyUser)
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
