package com.example.studhelper.retrofit

import com.example.studhelper.data.AccountCreds
import com.example.studhelper.data.EnterGroupRequest
import com.example.studhelper.data.GroupCreateRequest
import com.example.studhelper.data.GroupCreds
import com.example.studhelper.data.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserAPI {
    @POST("login")
    fun login(@Body accountCreds: AccountCreds): Call<Void>

    @POST("users/register")
    fun register(@Body registerRequest: RegisterRequest): Call<Void>

    @POST("group")
    fun createGroup(@Body groupCreateRequest: GroupCreateRequest): Call<GroupCreds>

    @PATCH("group")
    fun joinGroup(@Body enterGroupRequest: EnterGroupRequest): Call<Void>

    @GET("group")
    fun getGroup(): Call<GroupCreds>

    @DELETE("group")
    fun deleteGroup(): Call<Void>

    @PATCH("group/quit")
    fun exitGroup(): Call<Void>
}