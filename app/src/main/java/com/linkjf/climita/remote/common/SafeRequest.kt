package com.linkjf.climita.remote.common

import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Exception
import com.linkjf.climita.remote.common.Result.Success
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeRequest(
    execute: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Success(body)
        } else {
            Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Exception(e)
    }
}
