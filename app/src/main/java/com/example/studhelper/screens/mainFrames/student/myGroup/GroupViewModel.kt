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
import com.example.studhelper.screens.loginRegisterFrames.Routes


class GroupViewModel : ViewModel() {
    var groups by mutableStateOf(GroupsRepo.getGroups())
    var currentGroup by mutableStateOf(groups[0])
    fun getRandomString(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..10)
            .map { charset.random() }
            .joinToString("")
    }

    fun findGroup(groupName: String) {
        groups.forEach { item ->
            if (item.name == groupName) return
        }
    }

    fun addGroup(profile: Profile, group: String) {
        if (profile.group.name == "") {
            profile.group = Group(group, getRandomString())
            println(group)
        }
    }

    fun getGroup(profile: Profile): MutableList<Group> {
        val currentGroup = mutableListOf<Group>()
        groups.forEach { item ->
            if (item == profile.group) {
                currentGroup += listOf(profile.group)
            }
        }
        return currentGroup
    }

    fun setGroup(profile: Profile, code: String, navController: NavController) {
        groups.forEach { item ->
            if (item.code == code) {
                profile.group = item
            }
        }
        navController.navigate(Routes.Queue.route)
    }
}
