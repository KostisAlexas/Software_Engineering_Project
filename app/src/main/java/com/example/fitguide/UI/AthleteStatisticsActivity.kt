package com.example.fitguide.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitguide.R



//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.fitguide.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class AthleteStatisticsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_athlete_statistics)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // === Προσθήκη του γραφήματος ===
        val lineChart = findViewById<LineChart>(R.id.lineChart)

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 50f))
        entries.add(Entry(2f, 60f))
        entries.add(Entry(3f, 55f))
        entries.add(Entry(4f, 70f))
        entries.add(Entry(5f, 65f))

        val dataSet = LineDataSet(entries, "Εβδομαδιαία πρόοδος")
        dataSet.color = resources.getColor(R.color.purple_700, theme)
        dataSet.valueTextSize = 12f

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val desc = Description()
        desc.text = ""
        lineChart.description = desc

        lineChart.invalidate()
    }
}
