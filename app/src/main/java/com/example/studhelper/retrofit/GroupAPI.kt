package com.example.studhelper.retrofit

import com.example.studhelper.data.GetAllQueueRequest
import com.example.studhelper.data.GroupCreateRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupAPI {
    @POST("groups")
    fun createGroup(@Body groupCreateRequest: GroupCreateRequest): Call<Void>

    @GET("/groups/{group_id}/queues")
    fun getAllQueue(@Path("group_id") groupId: Int, @Body getAllQueueRequest: GetAllQueueRequest): Call<Void>

}