package dev.miran.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.miran.entity.HitsItem
import dev.miran.entity.ImageType
import dev.miran.usecase.HomeUseCase
import dev.miran.view_model.util.UiState
import dev.miran.view_model.util.launchInIo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val searchQuery = mutableStateOf("fruits")
    private var selectedImageType by mutableStateOf(ImageType.all)

    private val _imagesState = MutableStateFlow<UiState<List<HitsItem>>>(UiState.Idle)
    val imagesState = _imagesState.asStateFlow()

    init {
        initialLoad()
        loadLocalImages()
    }


    private fun initialLoad() {
        launchInIo {
            try {
                homeUseCase.initialLoad("fruits", ImageType.all.name)
            } catch (_: Exception) {

            }
        }
    }


    fun searchFieldOnChange(value: String) {
        searchQuery.value = value
    }

    fun setImageTypeFilter(imageType: ImageType) {
        selectedImageType = imageType
    }

    fun getSelectedFilterImageType(): ImageType =
        selectedImageType


    fun searchNewImages() {
        launchInIo {
            try {
                homeUseCase.clearAllImages()
                homeUseCase.getImageByQuery(searchQuery.value, selectedImageType.name)
            } catch (e: Exception) {
                Log.e(TAG, "searchNewImages: ", e)
            }
        }
    }

    private fun loadLocalImages() {
        launchInIo {
            _imagesState.update {
                UiState.Loading
            }
            try {
                homeUseCase.loadImages().collect { hitsItems ->
                    _imagesState.update {
                        UiState.Success(hitsItems)
                    }
                }

            } catch (e: Exception) {
                _imagesState.update {
                    UiState.Error(e)
                }
            }
        }
    }

    fun getSearchQuery() = searchQuery.value

    companion object {

    }

}