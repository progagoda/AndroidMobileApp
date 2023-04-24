package com.example.studhelper.retrofit

import com.example.studhelper.data.GroupCreateRequest
import com.example.studhelper.data.LoginRequest
import com.example.studhelper.data.Profile
import com.example.studhelper.data.Test
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<Profile>

    @POST("register")
    fun register(@Body profile: Profile): Call<Void>

    @POST("groups")
    fun createGroup(@Body groupCreateRequest: GroupCreateRequest): Call<Void>

    @POST("{user_id}/groups/{group_id}")
    fun enterGroup(@Path("user_id") userId: Int, @Path("group_id") groupId: Int): Call<Void>

    @DELETE("{user_id}/groups/{group_id}")
    fun exitGroup(@Path("user_id") userId: Int, @Path("group_id") groupId: Int): Call<Void>

    @GET("logout")
    fun logoutUser(@Body userId: Int): Call<Void>

    @GET("products/1")
    fun getchtoto(): Call<Test>
}