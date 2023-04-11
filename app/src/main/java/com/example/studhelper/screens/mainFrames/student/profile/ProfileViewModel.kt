package com.example.studhelper.screens.mainFrames.student.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.studhelper.data.Group
import com.example.studhelper.data.Profile
import com.example.studhelper.data.ProfilesRepo
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel

//TODO добавить метод проверки админ это или нет
class ProfileViewModel:ViewModel(){
    var profiles by mutableStateOf(ProfilesRepo.getProfiles())
    var currentProfile by mutableStateOf(profiles[0])
    fun addProfile(profile: Profile){
        if(profile.name=="" || profile.password.equals(null)|| profile.isu.equals(null)){
        }
        else {
            profiles = profiles + listOf(profile)
            println(profiles)
        }
    }
    fun setProfile(profile: Profile){
       currentProfile= profile;
    }
    fun profileList(group: Group): MutableList<Profile> {
        val groupList = mutableListOf<Profile>()
        profiles.forEach { item->
            if(item.group==group){
                groupList+= listOf(item)
            }
        }
        return groupList
    }
}