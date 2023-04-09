package com.example.studhelper.funtions

import com.example.studhelper.data.Profile
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
//TODO добавить функцию подрузки очередей текущего пользователя
fun loadGroup(
    profiles: List<Profile>,
    profileViewModel: ProfileViewModel,
    subject: MutableList<Profile>
) {
    profiles.forEach { item ->
        if (item.group == profileViewModel.currentProfile.group) subject.add(item)
    }
}