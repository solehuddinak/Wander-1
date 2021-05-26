package dev.dizzy1021.wander.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.PlaceUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PlaceUseCase
): ViewModel() {

    val places = { page: Int, user: String ->
        useCase.fetchHome(page, user).asLiveData()
    }

}