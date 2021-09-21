package com.manuelcaravantes.trackmyfitness.ui.detail

import androidx.lifecycle.ViewModel
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivityRepository
import com.manuelcaravantes.trackmyfitness.di.FitnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailScreenViewModel @Inject constructor(
    @FitnessRepository private val repository: FitnessActivityRepository
) : ViewModel() {

}