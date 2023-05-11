package com.example.studhelper.screens.mainFrames.student.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.studhelper.data.Group
import com.example.studhelper.data.Profile
import com.example.studhelper.data.ProfilesRepo
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.google.inject.Singleton

//TODO добавить метод проверки админ это или нет
@Singleton
class ProfileViewModel:ViewModel(){
    var profiles by mutableStateOf(ProfilesRepo.getProfiles())
    var currentProfile by mutableStateOf(profiles[0])
    fun addProfile(profile: Profile){
        if(profile.fullName == "" || profile.password.equals(null)|| profile.login.equals(null)){
        }
        else {
            //TODO здесь мы делаем запрос к бд на создание профиля
            profiles = profiles + listOf(profile)
            println(profiles)
        }
    }
    fun setProfile(profile: Profile){
        //TODO это оставляем на фронте, пусть текущий пользователь будет храниться здесь
       currentProfile= profile;
    }
    fun profileList(group: Group): MutableList<Profile> {
        val groupList = mutableListOf<Profile>()
        //TODO тут должна быть выгрузка профилей по группе
        profiles.forEach { item->
            if(item.group==group){
                groupList+= listOf(item)
            }
        }
        return groupList
    }
    fun logOutGroup(profileViewModel: ProfileViewModel, navHostController: NavHostController){
        //TODO здесь мы делаем запрос к бэку на удаление из группы конкретного пользователя
        profileViewModel.currentProfile.group = Group(name = "",code="")
        navHostController.navigate(Routes.ChooseGroup.route)
    }
}