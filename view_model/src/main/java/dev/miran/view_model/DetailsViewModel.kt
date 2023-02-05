package dev.miran.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.miran.entity.HitsItem
import dev.miran.usecase.ImageDetailsUseCase
import dev.miran.view_model.util.UiState
import dev.miran.view_model.util.launchInIo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(private val imageDetailsUseCase: ImageDetailsUseCase) :
    ViewModel() {


    private var selectedImageId by mutableStateOf(-1)

    private val _imageDetailsState = MutableStateFlow<UiState<HitsItem>>(UiState.Idle)
    val imageDetailsState = _imageDetailsState.asStateFlow()


    fun selectedImageId(id: Int) {
        selectedImageId = id
    }


    fun getImageById() {
        launchInIo {
            _imageDetailsState.update {
                UiState.Loading
            }
            try {
                val result = imageDetailsUseCase.getImageById(selectedImageId)

                if (result.isEmpty()) {
                    _imageDetailsState.update {
                        UiState.Error(Exception("No images where found"))
                    }
                    return@launchInIo
                }
                _imageDetailsState.update {
                    UiState.Success(result.first())
                }

            } catch (e: Exception) {
                Log.e(TAG, "getImageById: ", e)
                _imageDetailsState.update {
                    UiState.Error(e)
                }
            }
        }
    }
}