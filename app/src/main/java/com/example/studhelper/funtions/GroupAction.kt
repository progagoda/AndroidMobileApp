package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.studhelper.data.EnterGroupRequest
import com.example.studhelper.data.GroupCreateRequest
import com.example.studhelper.retrofit.UserAPI
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

class GroupAction {
    val interceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val userAPI: UserAPI = retrofit.create(UserAPI::class.java)

    fun createGroup(
        profileViewModel: ProfileViewModel,
        groupViewModel: GroupViewModel,
        groupNumber: MutableState<String>,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        if (groupNumber.value == "") {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Поле должно быть заполнено"
                )
            }
            return
        }
        val groupCreateRequest: GroupCreateRequest = GroupCreateRequest(groupNumber.value)
        userAPI.createGroup(groupCreateRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    TODO("Кажется, что использование GroupViewModel - херня какая-то")
//                    groupViewModel.addGroup(profileViewModel.currentProfile, groupNumber.value)
                    profileViewModel.currentProfile.admin = true
                    navController.navigate(Routes.Queue.route)
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

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
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
            profileViewModel.currentProfile.isu.toInt(),
            profileViewModel.currentProfile.group.name.toInt()
        )
        userAPI.enterGroup(enterGroupRequest.user_id, enterGroupRequest.group_id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        TODO("Непонятно, что делать на фронте при успешном подключении к группе на бэке")
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
                    TODO("Not yet implemented")
                }
            })
    }

    fun deleteGroup(
        profileViewModel: ProfileViewModel,
        groupViewModel: GroupViewModel,
        navController: NavHostController
    ) {
        TODO("Уточнить у Артёма, что делать в этом случае")

    }

}