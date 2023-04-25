package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.studhelper.data.DeleteGroupRequest
import com.example.studhelper.data.EnterGroupRequest
import com.example.studhelper.data.ExitGroupRequest
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

class UserAction {
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
                        TODO("Нужно использовать codeGroup")
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

    fun exitGroup(
        profileViewModel: ProfileViewModel,
        groupCode: MutableState<String>,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope
    ) {
        val exitGroupRequest = ExitGroupRequest(
            profileViewModel.currentProfile.isu.toInt(),
            profileViewModel.currentProfile.group.name.toInt()
        )
        userAPI.exitGroup(
            exitGroupRequest.userId,
            exitGroupRequest.groupId,
            exitGroupRequest
        ).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                TODO("Нужно придумать как удалить группу из ProfileViewModel" +
                        "Возможно, стоит добавить метод, который устанавливает в null")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}