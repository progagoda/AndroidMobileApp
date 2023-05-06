package com.example.studhelper.screens.mainFrames.student.queue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.studhelper.data.Profile
import com.example.studhelper.data.Subject
import com.example.studhelper.data.SubjectsRepo

class SubjectViewModel : ViewModel() {
    var subjects by mutableStateOf(SubjectsRepo.getSubjects())
    var currentSubject by mutableStateOf(subjects[0])
    fun addSubject(subject: Subject) {
        if (subject.name != "") {
            //TODO передаем бэку имя предмета на создание очереди
            subjects = subjects + listOf(subject)
            println(subjects)
        }
    }

    fun deleteSubject(subject: Subject) {
        //TODO передаем бэку имя предмета на удаление очереди
        subjects = subjects.toMutableList().also {
            it.remove(subject)
            println(subjects)
        }
    }

    fun subscribe(profile: Profile, subject: Subject) {
        //TODO передаем бэку профиль и объект предмета на подпись на очередь
        subjects = subjects.toMutableList().also {
            subject.students += listOf(profile)
            println(subjects)
        }
    }

    fun unsubscribe(profile: Profile, subject: Subject) {
        //TODO передаем бэку профиль и объект предмета на отпись от очереди
        subjects = subjects.toMutableList().also {
            subject.students -= listOf(profile)
            println(subjects)

        }
    }
}