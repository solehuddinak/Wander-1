package dev.dizzy1021.wander.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.PlaceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
@FlowPreview
class SearchViewModel @Inject constructor(
    private val useCase: PlaceUseCase
): ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val places = { page: Int, user: String, image: InputStream? ->

        queryChannel
            .asFlow()
            .debounce(700)
            .flatMapLatest {
                useCase.searchPlaces(page, user, it, image)
            }
            .asLiveData()
    }

}