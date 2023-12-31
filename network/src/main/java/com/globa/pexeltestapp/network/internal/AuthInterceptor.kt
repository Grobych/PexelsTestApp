package com.globa.pexeltestapp.network.internal

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("Authorization", key)
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}