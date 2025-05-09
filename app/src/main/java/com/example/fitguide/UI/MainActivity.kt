package com.example.fitguide.UI

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.fitguide.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var toolbar: Toolbar // Declare the Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // Keep or remove based on your preference for edge-to-edge
        setContentView(R.layout.activity_main)

        // Commenting out the insets listener for the main content as the Toolbar handles top inset
        // If you need to handle bottom insets for content below the Toolbar, adjust this.
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //     v.updatePadding(bottom = systemBars.bottom) // Only apply bottom padding to main content
        //     insets
        // }
        Log.d("MainActivity", "onCreate")

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Get reference to the Toolbar and set it as the Action Bar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Set the custom Toolbar as the Action Bar

        // Set up the ActionBarDrawerToggle using the Toolbar
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar, // Pass the Toolbar here
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    // This function is called when a menu item is selected in the NavigationView
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_view_workouts -> {
                // Handle the "View Workouts" menu item click (implement this later)
                val intent = Intent(this, ViewWorkoutsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_create_exercise -> {
                // Handle the "Create Exercise" menu item click
                val intent = Intent(this, CreateExerciseActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_view_exercises -> {
                // Handle the "View Exercises" menu item click
                val intent = Intent(this, ViewExercisesActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_statistics -> {
                // Handle the "Statistics" menu item click
                //val intent = Intent(this, StatisticsActivity::class.java)
                //startActivity(intent)
            }
            R.id.nav_profile -> {
                // Handle the "Profile" menu item click
                //val intent = Intent(this, ProfileActivity::class.java)
                //startActivity(intent)
            }
            R.id.nav_settings -> {
                // Handle the "Settings" menu item click
                //val intent = Intent(this, SettingsActivity::class.java)
                //startActivity(intent)
            }


            // Add more cases for other menu items
        }
        // Close the drawer after an item is selected
        drawerLayout.closeDrawer(GravityCompat.START)
        return true // Indicate that the item has been handled
    }

    // Handle the Up button click to open/close the drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Handle the back button press to close the drawer if it's open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}