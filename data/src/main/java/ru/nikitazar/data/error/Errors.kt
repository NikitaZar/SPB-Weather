package ru.nikitazar.data.error

import java.io.IOException
import java.net.UnknownHostException
import java.sql.SQLException

sealed class AppError(val code: Int, val info: String) : RuntimeException(info) {
    companion object {
        fun from(e: Throwable): AppError = when (e) {
            is AppError -> e
            is UnknownHostException -> NetworkError
            is IOException -> NetworkError
            else -> UnknownError
        }
    }
}

class ApiError(code: Int, message: String) : AppError(code, message)
object NetworkError : AppError(-1, "no_network")
object UnknownError : AppError(-2, "unknown")
