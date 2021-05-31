package dev.dizzy1021.wander.ui.place.feedback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.ReviewUseCase
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val useCase: ReviewUseCase
): ViewModel() {

    val reviews = { id: Int ->
        useCase.fetchReviewPlace(id).cachedIn(viewModelScope)
    }

}