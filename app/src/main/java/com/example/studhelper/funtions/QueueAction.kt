package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.studhelper.R
import com.example.studhelper.data.*
import com.example.studhelper.retrofit.GroupAPI
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Optional

class QueueAction(profileViewModel: ProfileViewModel) {
    val interceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
            val credentials: String = Credentials.basic(
                profileViewModel.currentProfile.login,
                profileViewModel.currentProfile.password
            )

            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", credentials)
                .build()
            chain.proceed(newRequest)
        }
        .build()
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://192.168.31.212:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val groupAPI: GroupAPI = retrofit.create(GroupAPI::class.java)
    fun getAllQueues(
        profileViewModel: ProfileViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) : Array<QueueCreds> {
        var aux: Array<QueueCreds> = emptyArray()
        groupAPI.getAllQueues().enqueue(object : Callback<QueueList> {
            override fun onResponse(call: Call<QueueList>, response: Response<QueueList>) {
                if (response.isSuccessful) {
                    aux = response.body()!!.queues
                } else {
                    val errorMessage: String = "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
//                    return response.body().queues
                }
            }

            override fun onFailure(call: Call<QueueList>, t: Throwable) {
                //TODO("Not yet implemented")
                //return subjectList
            }
        })
        return aux
    }

    fun createQueue(
        queueName: String,
        profileViewModel: ProfileViewModel,
        navController: NavController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        val createQueueRequest = CreateQueueRequest(queueName)
        groupAPI.createQueue(createQueueRequest).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    navController.navigate(Routes.Queue.route)
                }
                else {
                    val errorMessage: String = if (response.code() == 404)
                        "User is not in the group"
                    else
                        "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                //TODO("Not yet implemented")
            }
        })
    }

    fun getQueue(
        queueId: Int,
        profileViewModel: ProfileViewModel,
        groupViewModel: GroupViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) : Array<StudentInQueueCreds> {
        var aux : Array<StudentInQueueCreds> = emptyArray()
        groupAPI.getQueue(queueId).enqueue(object: Callback<StudentsInQueueList> {
            override fun onResponse(
                call: Call<StudentsInQueueList>,
                response: Response<StudentsInQueueList>
            ) {
                if (response.isSuccessful) {
                    aux = response.body()!!.students
                }
                else {
                    val errorMessage = "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<StudentsInQueueList>, t: Throwable) {
                //TODO("Not yet implemented")
            }
        })
        return aux
    }

    fun deleteQueue(
        queueId: Int,
        profileViewModel: ProfileViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        groupAPI.deleteQueue(queueId).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    getAllQueues(profileViewModel,navController,scaffoldState,coroutineScope)
                }
                else {
                    val errorMessage: String = if (response.code() == 400)
                        "Invalid data"
                    else
                        "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
              //  TODO("Not yet implemented")
                getAllQueues(profileViewModel,navController,scaffoldState,coroutineScope)
            }
        })
    }

    fun enterQueue(
        queueId: Int,
        profileViewModel: ProfileViewModel,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        groupAPI.enterQueue(queueId).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    //
                }
                else {
                    val errorMessage: String = if (response.code() == 404)
                        "Очереди с таким id нет"
                    else if (response.code() == 409)
                        "Пользователь уже записан в эту очередь"
                    else
                        "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun quitQueue(
        queueId: Int,
        profileViewModel: ProfileViewModel,
        subjectViewModel: SubjectViewModel,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) : List<Profile>{
        var aux: Array<Subject> = emptyArray()
        groupAPI.quitQueue(queueId).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    for (i in 0..subjectViewModel.currentSubject.students.size) {
                        val profile = subjectViewModel.currentSubject.students[i]
                        if (profile.login == profileViewModel.currentProfile.login) {
                            subjectViewModel.currentSubject.students.drop(i)
                            break
                        }
                    }
//                    return response.body()
                    // здесь нужно вернуть обновленный список subjectViewModel.currentSubject.students
                }
                else {
                    val errorMessage: String = if (response.code() == 404)
                        "Очереди с таким id нет"
                    else if (response.code() == 409)
                        "Пользователь не записан в эту очередь"
                    else
                        "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return subjectViewModel.currentSubject.students
    }
}