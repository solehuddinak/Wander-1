package dev.dizzy1021.wander.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.PlaceUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PlaceUseCase
): ViewModel() {

    val places = { user: String ->
        useCase.fetchHome(user).cachedIn(viewModelScope)
    }

}