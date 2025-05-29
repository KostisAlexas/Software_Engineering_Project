package com.example.fitguide.UI

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.Menu
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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- Add chart logic below ---
        val chart = findViewById<LineChart?>(R.id.lineChart)
        if (chart != null) {
            val days = 15
            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val calendar = Calendar.getInstance()
            val labels = mutableListOf<String>()
            val volumeEntries = mutableListOf<Entry>()
            val intensityEntries = mutableListOf<Entry>()

            for (i in (days - 1) downTo 0) {
                calendar.time = Date()
                calendar.add(Calendar.DAY_OF_YEAR, -i)
                val label = dateFormat.format(calendar.time)
                labels.add(label)
                // Dummy data
                val volume = (100..300).random().toFloat()
                val intensity = (50..150).random().toFloat()
                volumeEntries.add(Entry((days - i - 1).toFloat(), volume))
                intensityEntries.add(Entry((days - i - 1).toFloat(), intensity))
            }

            val volumeDataSet = LineDataSet(volumeEntries, "Workout Volume").apply {
                color = Color.BLUE
                setCircleColor(Color.BLUE)
                lineWidth = 2f
                valueTextColor = Color.BLUE
            }
            val intensityDataSet = LineDataSet(intensityEntries, "Workout Intensity").apply {
                color = Color.RED
                setCircleColor(Color.RED)
                lineWidth = 2f
                valueTextColor = Color.RED
            }

            val lineData = LineData(volumeDataSet, intensityDataSet)
            chart.data = lineData

            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            chart.xAxis.granularity = 1f
            chart.xAxis.labelRotationAngle = -45f
            chart.axisRight.isEnabled = false
            chart.description.isEnabled = false
            chart.legend.isEnabled = true
            chart.invalidate()
        }

        // --- Scheduled Workouts Section ---
        val scheduledWorkoutsSection = findViewById<LinearLayout>(R.id.scheduledWorkoutsSection)
        val workouts = listOf(
            Triple("Push Pull", "2024-06-10", "Δευτέρα"),
            Triple("Legs", "2024-06-12", "Τετάρτη"),
            Triple("Full Body", "2024-06-14", "Παρασκευή"),
            Triple("Cardio", "2024-06-16", "Κυριακή")
        )

        for ((name, date, dayName) in workouts) {
            val workoutView = LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_2, scheduledWorkoutsSection, false)
            val text1 = workoutView.findViewById<TextView>(android.R.id.text1)
            val text2 = workoutView.findViewById<TextView>(android.R.id.text2)
            text1.text = name
            text2.text = "$date  •  $dayName"
            scheduledWorkoutsSection.addView(workoutView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                // Handle your button click here
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
