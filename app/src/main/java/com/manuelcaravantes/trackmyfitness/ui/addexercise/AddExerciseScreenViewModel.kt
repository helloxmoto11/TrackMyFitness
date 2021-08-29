package com.manuelcaravantes.trackmyfitness.ui.addexercise

import androidx.lifecycle.ViewModel
import com.manuelcaravantes.trackmyfitness.data.model.FakeExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseScreenViewModel @Inject constructor(
    private val repository: FakeExerciseRepository
) : ViewModel() {

}