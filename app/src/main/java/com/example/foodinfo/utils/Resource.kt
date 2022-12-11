package com.example.foodinfo.utils

sealed class Resource<T>(
    val data: T, // дата не должна быть обязательной, но мне не охота решать конфликты типов везде :)
    val message: String? = null,
    val error: Exception? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T) : Resource<T>(data)
    class Error<T>(error: Exception, message: String,  data: T) :
        Resource<T>(data, message, error)
    class Init<T>(data: T) : Resource<T>(data)
}