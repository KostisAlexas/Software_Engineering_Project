package com.example.fitguide.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitguide.R
import com.example.fitguide.domain.model.Exercise
import com.example.fitguide.repository.ExerciseRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ViewExercisesActivity : AppCompatActivity() {

    companion object {
        const val MODE_VIEW = "mode_view"
        const val MODE_PICK = "mode_pick"

        const val CREATE_EXERCISE_REQUEST = 100
        const val EXTRA_SELECTED_EXERCISE = "selected_exercise"
        const val EXTRA_MODE = "mode"
    }


    @Inject lateinit var exerciseRepository: ExerciseRepository
    private lateinit var adapter: ViewExercisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercises)

        val mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_VIEW

        // Setup RecyclerView
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.exercises_recycler_view)

        // Setup adapter
        adapter = ViewExercisesAdapter(emptyList()) { exercise ->
            if (mode == MODE_PICK) {
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_SELECTED_EXERCISE, exercise)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Default view mode behavior, e.g., show details
                Toast.makeText(this, "Exercise: ${exercise.name}", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // New button for creating an exercise
        val createButton = findViewById<Button>(R.id.create_exercise_button)
        createButton.setOnClickListener {
            startActivityForResult(Intent(this, CreateExerciseActivity::class.java), CREATE_EXERCISE_REQUEST)
        }

        // Dummy userId value; adjust as needed
        val userId = 0L
        lifecycleScope.launch {
            exerciseRepository.getAllExercises(userId).collect { exercises ->
                adapter.updateData(exercises)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_EXERCISE_REQUEST && resultCode == RESULT_OK) {
            val newExercise = data?.getParcelableExtra<Exercise>("new_exercise")
            newExercise?.let {
                adapter.addExercise(it)
            }
        }

    }
}
