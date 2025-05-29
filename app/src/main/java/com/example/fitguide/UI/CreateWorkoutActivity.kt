package com.example.fitguide.UI

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fitguide.R
import com.example.fitguide.domain.model.Exercise
import com.example.fitguide.domain.model.ExerciseType
import com.example.fitguide.domain.model.ScheduledExercise
import com.example.fitguide.domain.model.ScheduledSet
import com.example.fitguide.domain.model.Workout

class CreateWorkoutActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_PICK_EXERCISE = 1
        const val EXTRA_SELECTED_EXERCISE = "selected_exercise"
    }

    private lateinit var addExerciseButton: Button
    private lateinit var formContainer: LinearLayout
    private val exerciseForms = mutableListOf<Pair<Exercise, View>>() // Pair of Exercise and its form view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)

        addExerciseButton = findViewById(R.id.addExerciseButton)
        formContainer = findViewById(R.id.scheduledExerciseFormContainer)

        addExerciseButton.setOnClickListener {
            val intent = Intent(this, ViewExercisesActivity::class.java).apply {
                putExtra(ViewExercisesActivity.EXTRA_MODE, ViewExercisesActivity.MODE_PICK)
            }
            startActivityForResult(intent, REQUEST_CODE_PICK_EXERCISE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_EXERCISE && resultCode == RESULT_OK) {
            val selectedExercise = data?.getParcelableExtra<Exercise>(EXTRA_SELECTED_EXERCISE)
            if (selectedExercise != null) {
                addExerciseForm(selectedExercise)
            }
        }
    }

    private fun addExerciseForm(exercise: Exercise) {
        val exerciseFormView = layoutInflater.inflate(R.layout.item_scheduled_exercise_form, formContainer, false)
        val exerciseNameTextView = exerciseFormView.findViewById<TextView>(R.id.exerciseNameTextView)
        exerciseNameTextView.text = exercise.name

        val addSetButton = exerciseFormView.findViewById<Button>(R.id.addSetButton)
        val setsContainer = exerciseFormView.findViewById<LinearLayout>(R.id.setsContainer)
        addSetButton.setOnClickListener {
            addSetFormFor(exercise.type, setsContainer)
        }

        // Optionally, add a remove button for each exercise form
        val removeButton = Button(this)
        removeButton.text = "Remove"
        (exerciseFormView as LinearLayout).addView(removeButton)
        removeButton.setOnClickListener {
            formContainer.removeView(exerciseFormView)
            exerciseForms.removeIf { it.second == exerciseFormView }
        }

        formContainer.addView(exerciseFormView)
        exerciseForms.add(Pair(exercise, exerciseFormView))
    }

    private fun addSetFormFor(exType: ExerciseType, setsContainer: LinearLayout) {
        val inflater = LayoutInflater.from(this)
        val setView: View = when (exType) {
            ExerciseType.WEIGHTED ->
                inflater.inflate(R.layout.item_set_weighted, setsContainer, false)
            ExerciseType.REPS ->
                inflater.inflate(R.layout.item_set_reps, setsContainer, false)
            ExerciseType.DURATION ->
                inflater.inflate(R.layout.item_set_duration, setsContainer, false)
            ExerciseType.DISTANCE ->
                inflater.inflate(R.layout.item_set_distance, setsContainer, false)
        }
        setsContainer.addView(setView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_workout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_workout -> {
                saveWorkout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveWorkout() {
        if (exerciseForms.isEmpty()) {
            Toast.makeText(this, "Add at least one exercise to save the workout.", Toast.LENGTH_SHORT).show()
            return
        }

        val scheduledExercises = mutableListOf<ScheduledExercise>()
        for ((exercise, formView) in exerciseForms) {
            val setsContainer = formView.findViewById<LinearLayout>(R.id.setsContainer)
            val sets = mutableListOf<ScheduledSet>()

            for (i in 0 until setsContainer.childCount) {
                val setView = setsContainer.getChildAt(i)
                when (exercise.type) {
                    ExerciseType.WEIGHTED -> {
                        val repsEdit = setView.findViewById<EditText?>(R.id.repsEditText)
                        val weightEdit = setView.findViewById<EditText?>(R.id.weightEditText)
                        val reps = repsEdit?.text?.toString()?.toIntOrNull() ?: continue
                        val weight = weightEdit?.text?.toString()?.toFloatOrNull() ?: continue
                        sets.add(ScheduledSet.WeightedSet(reps = reps, weightKg = weight))
                    }
                    ExerciseType.REPS -> {
                        val repsEdit = setView.findViewById<EditText?>(R.id.repsEditText)
                        val reps = repsEdit?.text?.toString()?.toIntOrNull() ?: continue
                        sets.add(ScheduledSet.RepsSet(reps = reps))
                    }
                    ExerciseType.DURATION -> {
                        val durationEdit = setView.findViewById<EditText?>(R.id.durationSecondsEditText)
                        val duration = durationEdit?.text?.toString()?.toIntOrNull() ?: continue
                        sets.add(ScheduledSet.DurationSet(durationSeconds = duration))
                    }
                    ExerciseType.DISTANCE -> {
                        val distanceEdit = setView.findViewById<EditText?>(R.id.distanceMetersEditText)
                        val durationEdit = setView.findViewById<EditText?>(R.id.durationSecondsEditText)
                        val distance = distanceEdit?.text?.toString()?.toIntOrNull() ?: continue
                        val duration = durationEdit?.text?.toString()?.toIntOrNull() ?: continue
                        sets.add(ScheduledSet.DistanceSet(distanceMeters = distance, durationSeconds = duration))
                    }
                }
            }

            if (sets.isEmpty()) {
                Toast.makeText(this, "Each exercise must have at least one set.", Toast.LENGTH_SHORT).show()
                return
            }

            scheduledExercises.add(
                ScheduledExercise(
                    exercise = exercise,
                    sets = sets
                )
            )
        }

        // Prompt for workout name and description
        val dialogLayout = LinearLayout(this)
        dialogLayout.orientation = LinearLayout.VERTICAL
        val nameEdit = EditText(this)
        nameEdit.hint = "Workout name"
        val descEdit = EditText(this)
        descEdit.hint = "Description (optional)"
        dialogLayout.addView(nameEdit)
        dialogLayout.addView(descEdit)

        AlertDialog.Builder(this)
            .setTitle("Save Workout")
            .setView(dialogLayout)
            .setPositiveButton("Save") { _, _ ->
                val workoutName = nameEdit.text.toString().ifBlank { "Workout" }
                val workoutDesc = descEdit.text.toString()
                val workout = Workout(
                    name = workoutName,
                    description = workoutDesc,
                    ownerId = 1L, // Replace with actual user id if available
                    exercises = scheduledExercises
                )
                // TODO: Save workout to repository or pass back as result
                Toast.makeText(this, "Workout saved: ${workout.name} (${workout.exercises.size} exercises)", Toast.LENGTH_LONG).show()
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
