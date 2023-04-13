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
    var currentSubject by mutableStateOf(subjects[0])
    fun addSubject(subject: Subject) {
        if (subject.name != "") {
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
        subjects = subjects.toMutableList().also {
            subject.students += listOf(profile)
            println(subjects)
        }
    }

    fun unsubscribe(profile: Profile, subject: Subject) {
        subjects = subjects.toMutableList().also {
            subject.students -= listOf(profile)
            println(subjects)

        }
    }
}