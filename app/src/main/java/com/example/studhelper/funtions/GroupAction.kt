package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.studhelper.data.DeleteGroupRequest
import com.example.studhelper.data.GroupCreateRequest
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
    val groupAPI: GroupAPI = retrofit.create(GroupAPI::class.java)

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
        groupAPI.createGroup(groupCreateRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
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

    fun deleteGroup(
        profileViewModel: ProfileViewModel,
        groupViewModel: GroupViewModel,
        navController: NavHostController
    ) {
        val deleteGroupRequest: DeleteGroupRequest = DeleteGroupRequest(
            profileViewModel.currentProfile.group.name.toInt()
        )
        TODO("Нужно передавать userId и groupId, т.к. на бэке нужно проверять наличие прав админа, чтобы удалять")
    }
}