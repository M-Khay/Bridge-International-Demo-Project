package com.bridge.androidtechnicaltest.network


sealed class ApiResult<out T : Any> {
    abstract val isLoading: Boolean
}

data class Success<out T : Any>(val data: T , val totalPages: Int,  override val isLoading: Boolean) : ApiResult<T>()
data class Error(val exception: Exception? , override val isLoading: Boolean) : ApiResult<Nothing>()
data class Loading(override val isLoading: Boolean) : ApiResult<Nothing>()
