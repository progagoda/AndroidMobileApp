package com.example.studhelper.screens.mainFrames.student.queue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.studhelper.data.Profile
import com.example.studhelper.data.Subject
import com.example.studhelper.data.SubjectsRepo

//TODO сделать методы записи и отписи от очереди
class SubjectViewModel : ViewModel() {
    var subjects by mutableStateOf(SubjectsRepo.getSubjects())
    fun addSubject(subject: Subject) {
        if (subject.name == "") {
        } else {
            subjects = subjects + listOf(subject)
            println(subjects)
        }
    }

    fun deleteSubject(subject: Subject) {
        subjects = subjects.toMutableList().also {
            it.remove(subject)
            println(subjects)
        }
    }

    fun subscribe(profile: Profile, subject: Subject) {
        subjects.forEach { item ->
            if (item == subject) item.students += listOf<Profile>(profile)
        }
    }

    fun unsubscribe(profile: Profile, subject: Subject) {
        subjects.forEach { item ->
            if (item == subject) {
                item.students.forEach { profileItem ->
//
                }

            }
        }
    }
}