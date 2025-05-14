package com.example.fitguide.UI

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import android.content.Intent
import com.example.fitguide.R
import com.example.fitguide.domain.model.Exercise
import com.example.fitguide.domain.model.ExerciseType
import com.example.fitguide.domain.model.MuscleGroup
import com.example.fitguide.repository.ExerciseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateExerciseActivity : AppCompatActivity() {
    @Inject
    lateinit var exerciseRepository: ExerciseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val primaryBtn = findViewById<Button>(R.id.primary_muscle_groups)
        val secondaryBtn = findViewById<Button>(R.id.secondary_muscle_groups)
        val exerciseTypeBtn = findViewById<Button>(R.id.exercise_types)
        val primarySelected = findViewById<TextView>(R.id.primary_muscle_groups_selected)
        val secondarySelected = findViewById<TextView>(R.id.secondary_muscle_groups_selected)
        val exerciseTypeSelected = findViewById<TextView>(R.id.exercise_types_selected)
        val muscleGroupNames = MuscleGroup.values().map { it.name.replace("_", " ") }.toTypedArray()
        val exerciseTypeNames = ExerciseType.values().map { it.name.replace("_", " ") }.toTypedArray()
        val exerciseNameField = findViewById<TextView>(R.id.exercise_name)
        val exerciseDescriptionField = findViewById<TextView>(R.id.exercise_description)
        val saveButton = findViewById<Button>(R.id.save_exercise_button)

        primaryBtn.setOnClickListener {
            val selectedItems = BooleanArray(muscleGroupNames.size)
            AlertDialog.Builder(this)
                .setTitle("Select Primary Muscle Groups")
                .setMultiChoiceItems(muscleGroupNames, selectedItems) { _, which, isChecked ->
                    selectedItems[which] = isChecked
                }
                .setPositiveButton("OK") { dialog, _ ->
                    val selected = muscleGroupNames.filterIndexed { index, _ -> selectedItems[index] }
                    primarySelected.text = if (selected.isNotEmpty()) selected.joinToString(", ") else "None selected"
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        secondaryBtn.setOnClickListener {
            val selectedItems = BooleanArray(muscleGroupNames.size)
            AlertDialog.Builder(this)
                .setTitle("Select Secondary Muscle Groups")
                .setMultiChoiceItems(muscleGroupNames, selectedItems) { _, which, isChecked ->
                    selectedItems[which] = isChecked
                }
                .setPositiveButton("OK") { dialog, _ ->
                    val selected = muscleGroupNames.filterIndexed { index, _ -> selectedItems[index] }
                    secondarySelected.text = if (selected.isNotEmpty()) selected.joinToString(", ") else "None selected"
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        exerciseTypeBtn.setOnClickListener {
            var selectedIndex = -1
            AlertDialog.Builder(this)
                .setTitle("Select Exercise Type")
                .setSingleChoiceItems(exerciseTypeNames, -1) { _, which ->
                    selectedIndex = which
                }
                .setPositiveButton("OK") { dialog, _ ->
                    exerciseTypeSelected.text = if (selectedIndex != -1) exerciseTypeNames[selectedIndex] else "None selected"
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        saveButton.setOnClickListener {
            val name = exerciseNameField.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Exercise name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val description = exerciseDescriptionField.text.toString().trim()

            // Convert selected primary muscle groups from string to enum value
            val primaryList = primarySelected.text.toString().split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() && it != "None selected" }
                .mapNotNull { str ->
                    try {
                        MuscleGroup.valueOf(str.replace(" ", "_").toUpperCase())
                    } catch (e: Exception) {
                        null
                    }
                }
            // Convert selected secondary muscle groups
            val secondaryList = secondarySelected.text.toString().split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() && it != "None selected" }
                .mapNotNull { str ->
                    try {
                        MuscleGroup.valueOf(str.replace(" ", "_").toUpperCase())
                    } catch (e: Exception) {
                        null
                    }
                }

            // Convert selected exercise type string to ExerciseType enum.
            val exerciseType = try {
                ExerciseType.valueOf(
                    exerciseTypeSelected.text.toString()
                        .replace(" ", "_")
                        .toUpperCase()
                )
            } catch (e: Exception) {
                // Use a default or handle error as needed.
                ExerciseType.values().first()
            }

            // Create a new Exercise object
            val newExercise = Exercise(
                name = name,
                description = if (description.isNotEmpty()) description else null,
                type = exerciseType,
                primaryMuscleGroups = primaryList,
                secondaryMuscleGroups = secondaryList
            )

            // Save exercise asynchronously and return result
            lifecycleScope.launch {
                val newId = exerciseRepository.insertExercise(newExercise)
                val newExerciseWithId = newExercise.copy(id = newId)
                val resultIntent = Intent().apply { 
                    putExtra("new_exercise", newExerciseWithId)
                }
                setResult(RESULT_OK, resultIntent)
                Toast.makeText(this@CreateExerciseActivity, "Exercise saved!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
