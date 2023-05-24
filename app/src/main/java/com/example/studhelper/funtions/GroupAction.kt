package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.studhelper.data.EnterGroupRequest
import com.example.studhelper.data.Group
import com.example.studhelper.data.GroupCreateRequest
import com.example.studhelper.data.GroupCreds
import com.example.studhelper.retrofit.UserAPI
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
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

class GroupAction(profileViewModel: ProfileViewModel) {
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
    val userAPI: UserAPI = retrofit.create(UserAPI::class.java)

    fun createGroup(
        profileViewModel: ProfileViewModel,
        groupNumber: String,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        if (groupNumber == "") {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Поле должно быть заполнено"
                )
            }
            return
        }
        val groupCreateRequest: GroupCreateRequest = GroupCreateRequest(groupNumber)
        var sendObject = Group(groupNumber,"123")
        userAPI.createGroup(groupCreateRequest).enqueue(object : Callback<GroupCreds> {
            override fun onResponse(call: Call<GroupCreds>, response: Response<GroupCreds>) {
                if (response.isSuccessful) {
                    val groupCreds = response.body()
                    if (groupCreds != null) {
                        sendObject.code = response.body()!!.inviteCode!!
                        profileViewModel.currentProfile.group= sendObject
                        profileViewModel.currentProfile.admin= true
                        navController.navigate(Routes.Queue.route)
                    };
                }
                else {
                    val errorMessage: String = if (response.code() == 400)
                        "Bad input parameter"
                    else if (response.code() == 409)
                        "Пользователь уже является старостой группы"
                    else
                        "Unknown error"
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage
                        )
                    }
                }
            }

            override fun onFailure(call: Call<GroupCreds>, t: Throwable) {
                profileViewModel.currentProfile.group= sendObject
                profileViewModel.currentProfile.admin= true
                navController.navigate(Routes.Queue.route)
                //TODO("Not yet implemented")
            }
        })
    }


    // TODO("Узнать по поводу group code и сгладить несостыковки с backend")
    fun joinGroup(
        profileViewModel: ProfileViewModel,
        groupCode: MutableState<String>,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        if (groupCode.value == "") {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Поле должно быть заполнено"
                )
            }
            return
        }
        val enterGroupRequest: EnterGroupRequest = EnterGroupRequest(
            groupCode.value
        )
        val sendObject = Group("123","123")
        userAPI.joinGroup(enterGroupRequest)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        profileViewModel.currentProfile.group=sendObject
                        profileViewModel.currentProfile.admin = false
                        navController.navigate(Routes.Queue.route)
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
                    profileViewModel.currentProfile.group=sendObject
                    profileViewModel.currentProfile.admin = false
                    navController.navigate(Routes.Queue.route)
//                    //TODO("Not yet implemented")
                }
            })
    }

    fun getGroup(
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        userAPI.getGroup().enqueue(object: Callback<GroupCreds> {
            override fun onResponse(call: Call<GroupCreds>, response: Response<GroupCreds>) {
                if (response.isSuccessful) {
//                   return response.body()
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

            override fun onFailure(call: Call<GroupCreds>, t: Throwable) {
               // TODO("Not yet implemented")
            }
        })
    }

    fun deleteGroup(
        profileViewModel: ProfileViewModel,
        groupViewModel: GroupViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        userAPI.deleteGroup().enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    //
                }
                else {
                    val errorMessage: String = if (response.code() == 400)
                        "invalid input, object invalid"
                    else if (response.code() == 403)
                        "пользователь не является старостой данной группы"
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

    fun exitGroup(
        profileViewModel: ProfileViewModel,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        userAPI.exitGroup().enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    navController.navigate(Routes.Login.route)
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
                //TODO("Not yet implemented")
            }
        })
    }

}