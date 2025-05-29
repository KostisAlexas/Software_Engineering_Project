package com.example.fitguide.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitguide.R
import com.example.fitguide.domain.model.Exercise

class ViewExercisesAdapter(private var exercises: List<Exercise>, private val onExerciseClick: ((Exercise) -> Unit)? = null
    ) : RecyclerView.Adapter<ViewExercisesAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val exerciseType: TextView = itemView.findViewById(R.id.exercise_type)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.exerciseName.text = exercises[position].name
        holder.exerciseType.text = exercises[position].type.name
        holder.itemView.setOnClickListener {
            onExerciseClick?.invoke(exercises[position])
        }
    }

    override fun getItemCount(): Int = exercises.size

    fun updateData(newExercises: List<Exercise>) {
        exercises = newExercises
        notifyDataSetChanged()
    }

    fun addExercise(exercise: Exercise) {
        exercises = listOf(exercise) + exercises
        notifyItemInserted(0)
    }
}
