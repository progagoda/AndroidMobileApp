package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.studhelper.data.AccountCreds
import com.example.studhelper.data.Group
import com.example.studhelper.data.Profile
import com.example.studhelper.data.RegisterRequest
import com.example.studhelper.retrofit.UserAPI
import com.example.studhelper.screens.loginRegisterFrames.Routes
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


fun redirect(url: () -> Unit) {
    url()
}


class LoginAction() {
    val interceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://192.168.31.212:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val userAPI: UserAPI = retrofit.create(UserAPI::class.java)

    fun register(
        isu: MutableState<String>,
        name: MutableState<String>,
        surname: MutableState<String>,
        password: MutableState<String>,
        scaffoldState: ScaffoldState,
        coroutineScope: CoroutineScope,
        navController: NavController,
        profileViewModel: ProfileViewModel
    ) {
        if(isu.value.isEmpty() || name.value.isEmpty()  || surname.value.isEmpty()|| password.value.isEmpty()){
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Все поля должны быть заполнены"
                )
            }
            return
        }

        val currentProfile = (Profile(
            name = "${name.value} ${surname.value}",
            isu = isu.value,
            password = password.value,
            admin = false,
            group = Group(name="", code="")
        ))
        val registerRequest = RegisterRequest(
            "anatoly12",
            "12345",
            "Anatoly Britkov"
        )
        userAPI.register(registerRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    profileViewModel.currentProfile = currentProfile
                    navController.navigate(Routes.ChooseGroup.route)
                }
                else {
                    val errorMessage: String = if (response.code() == 400)
                        "User exists"
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
                coroutineScope.launch {
                    t.message?.let {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it
                        )
                    }
                }
            }
        })
    }

    fun checkUser(
        login: String,
        password: String,
        navController: NavController,
        coroutineScope: CoroutineScope,
        scaffoldState: ScaffoldState,
        profileViewModel: ProfileViewModel
    ) {
        var currentProfile: Profile? = null;
        if (login == "" || password == ""){
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Поля не могут быть пустыми"
                )
            }
        } else {
            val accountCreds: AccountCreds = AccountCreds(login, password)
            userAPI.login(accountCreds).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
//                        currentProfile = Profile(
//                            name = accountCreds.username,
//                            password = accountCreds.password
//                        )
//                        profileViewModel.currentProfile = currentProfile!!
                        redirect { navController.navigate(Routes.ChooseGroup.route) }
                    } else {
                        val errorMessage = if (response.code() == 400)
                            "Invalid username or password"
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
}