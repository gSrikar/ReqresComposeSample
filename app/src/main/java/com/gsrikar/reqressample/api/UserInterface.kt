package com.gsrikar.reqressample.api

import com.gsrikar.reqressample.models.ReqresResponse
import retrofit2.http.GET


/**
 * Retrofit API Interface
 */
interface UserInterface {

    @GET("users?delay=3")
    suspend fun getUserList(): ReqresResponse

}