package com.example.cleanarchitecturemvvm.presentation.ui.auth.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Fail<out T : Any>(val data: T) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Fail<*> ->"Failed[data=$data]"
        }
    }
}