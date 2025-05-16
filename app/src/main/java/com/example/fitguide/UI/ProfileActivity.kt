package com.example.fitguide.UI

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitguide.R
import com.example.fitguide.domain.model.User
import com.example.fitguide.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    @Inject lateinit var userRepository: UserRepository
    lateinit var user: User


    // Declare views to display user's info
    private lateinit var firstNameValue: TextView
    private lateinit var lastNameValue: TextView
    private lateinit var emailValue: TextView
    private lateinit var genderValue: TextView
    private lateinit var ageValue: TextView
    private lateinit var heightValue: TextView
    private lateinit var weightValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        // Initialize views
        firstNameValue = findViewById(R.id.first_name_value)
        lastNameValue = findViewById(R.id.last_name_value)
        genderValue = findViewById(R.id.gender_value)
        heightValue = findViewById(R.id.height_value)
        weightValue = findViewById(R.id.weight_value)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_edit -> {
                // Handle edit action (e.g., show edit UI)
                Toast.makeText(this, "Edit pressed", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
