package dev.miran.view_model.util

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val values: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}
