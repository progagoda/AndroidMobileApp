package com.example.studhelper.retrofit

import com.example.studhelper.data.CreateQueueRequest
import com.example.studhelper.data.QueueList
import com.example.studhelper.data.StudentsInQueueList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupAPI {

    @GET("/group/queues")
    fun getAllQueues(): Call<QueueList>

    @POST("/group/queues")
    fun createQueue(@Body createQueueRequest: CreateQueueRequest): Call<Void>

    @GET("/group/queues/{queueId}")
    fun getQueue(@Path("queueId") queueId: Int): Call<StudentsInQueueList>

    @DELETE("/group/queues/{queueId}")
    fun deleteQueue(@Path("queueId") queueId: Int): Call<Void>

    @PATCH("/group/queues/{queueId}")
    fun enterQueue(@Path("queueId") queueId: Int): Call<Void>

    @PATCH("/group/queues/{queueId}/quit")
    fun quitQueue(@Path("queueId") queueId: Int): Call<Void>
}