package com.example.studhelper.funtions

import com.example.studhelper.data.Profile
import com.example.studhelper.data.Subject
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel

fun loadGroup(
    profileViewModel: ProfileViewModel,
): MutableList<Profile> {
    val profiles = mutableListOf<Profile>()
    profileViewModel.profiles.forEach { item ->
        if (item.group == profileViewModel.currentProfile.group) profiles.add(item)
    }
    return profiles
}

fun loadQueue(
    profileViewModel: ProfileViewModel,
    subjectViewModel: SubjectViewModel,
): MutableList<Subject> {
    val subjects = mutableListOf<Subject>()
    subjectViewModel.subjects.forEach { item->
        if(profileViewModel.currentProfile.group==item.admin.group){
           subjects.add(item)
            println(subjects)
        }
    }
    return subjects
}