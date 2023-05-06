package com.example.studhelper.screens.mainFrames.student.myGroup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.studhelper.data.Group
import com.example.studhelper.data.GroupsRepo
import com.example.studhelper.data.Profile
import com.example.studhelper.data.Subject
import com.example.studhelper.screens.loginRegisterFrames.Routes


class GroupViewModel : ViewModel() {
    var groups by mutableStateOf(GroupsRepo.getGroups())

    fun findGroup(groupName: String) {
        //TODO получение группы по имени
    }

    fun addGroup(profile: Profile, group: String) {
        //TODO создание группы по имени + ее админ текущий переданный пользователь
    }

    fun getGroup(profile: Profile) {
        //TODO получить группу конкретного пользователя
    }

    fun setGroup(profile: Profile, code: String):Boolean {
        //TODO задать группу по коду определенному пользователю
        return true
    }
    fun deleteGroup(group: Group) {
        }
    }
