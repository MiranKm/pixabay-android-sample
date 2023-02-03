package dev.miran.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.miran.entity.HitsItem
import dev.miran.usecase.HomeUseCase
import dev.miran.view_model.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val searchQuery = mutableStateOf("fruits")

    private val _imagesState = MutableStateFlow<UiState<List<HitsItem>>>(UiState.Idle)
    val imagesState = _imagesState.asStateFlow()

    init {
        initialLoad()
        loadLocalImages()
    }


    private fun initialLoad() {


        viewModelScope.launch(Dispatchers.IO) {
            try{
                homeUseCase.initialLoad("fruits")

            }catch (e:Exception){

            }
        }
    }


    fun searchFieldOnChange(value: String) {
        searchQuery.value = value
    }

    fun searchNewImages() {
        viewModelScope.launch {
            try{
                homeUseCase.getImageByQuery(searchQuery.value)
            }catch (e:Exception){

            }
        }
        loadLocalImages()
    }

    private fun loadLocalImages() {
        _imagesState.update {
            UiState.Loading
        }
        viewModelScope.launch {
            try {
                homeUseCase.loadImages().collect { hitImages ->
                    _imagesState.update {
                        UiState.Success(hitImages)
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
}