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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QueueAction {
    val interceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
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
    ): MutableList<Subject> {
        val subjectList = mutableListOf<Subject>(
            Subject(
                name = "Методы и средства программной инженерии",
                admin = Profile(
                    "Артем Сергеевич",
                    GroupsRepo.getGroups()[0],
                    R.drawable.avatar_bad_breaking,
                    "285384",
                    "12345",
                    true
                ),
                students = listOf<Profile>(
                    Profile(
                        "Артем Ваховскович",
                        GroupsRepo.getGroups()[0],
                        R.drawable.avatar_bad_breaking,
                        "285381",
                        "12345",
                        false
                    ),
                    Profile(
                        "Артем Крисанович",
                        GroupsRepo.getGroups()[0],
                        R.drawable.avatar_bad_breaking,
                        "285382",
                        "12345",
                        false
                    ),
                )
            )
        )
//        navController.navigate(Routes.Queue.route)
        return subjectList
        groupAPI.getAllQueues().enqueue(object: Callback<QueueList> {
            override fun onResponse(call: Call<QueueList>, response: Response<QueueList>) {
                if (response.isSuccessful) {
//                    return subjectList
                }
                else {
                    val errorMessage: String = "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
//                    return subjectList;
                }
            }

            override fun onFailure(call: Call<QueueList>, t: Throwable){
                //TODO("Not yet implemented")
                //return subjectList
            }
        })
    }

    fun createQueue(
        queueName: String,
        profileViewModel: ProfileViewModel,
        navController: NavController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ): MutableList<Subject> {
        val createQueueRequest = CreateQueueRequest(queueName)
        val subjectList = mutableListOf<Subject>(
            Subject(
                name = "Методы и средства программной инженерии",
                admin = Profile(
                    "Артем Сергеевич",
                    GroupsRepo.getGroups()[0],
                    R.drawable.avatar_bad_breaking,
                    "285384",
                    "12345",
                    true
                ),
                students = listOf<Profile>(
                    Profile(
                        "Артем Ваховскович",
                        GroupsRepo.getGroups()[0],
                        R.drawable.avatar_bad_breaking,
                        "285381",
                        "12345",
                        false
                    ),
                    Profile(
                        "Артем Крисанович",
                        GroupsRepo.getGroups()[0],
                        R.drawable.avatar_bad_breaking,
                        "285382",
                        "12345",
                        false
                    ),
                )
            )
        )
        var addSubject = Subject(2,"asda",profileViewModel.currentProfile,profileViewModel.profiles)
        subjectList.add(addSubject)
        navController.navigate(Routes.Queue.route)
        return subjectList
        groupAPI.createQueue(createQueueRequest).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    //
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
                TODO("Not yet implemented")
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
    ) {
        groupAPI.getQueue(queueId).enqueue(object: Callback<StudentsInQueueList> {
            override fun onResponse(
                call: Call<StudentsInQueueList>,
                response: Response<StudentsInQueueList>
            ) {
                if (response.isSuccessful) {
                    //
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
                TODO("Not yet implemented")
            }
        })
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
        groupViewModel: GroupViewModel,
        navController: NavHostController,
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
        groupViewModel: GroupViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        groupAPI.quitQueue(queueId).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    //
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
    }
}