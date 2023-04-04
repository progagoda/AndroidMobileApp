package com.example.studhelper.screens.mainFrames.student.queue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.studhelper.components.Warning
import com.example.studhelper.data.Subject
import com.example.studhelper.data.SubjectsRepo

class SubjectViewModel:ViewModel(){
    var subjects by mutableStateOf(SubjectsRepo.getSubjects())
    fun addSubject(subject: Subject){
        if(subject.name==""){
        }
        else {
            subjects = subjects + listOf(subject)
        }
    }
    fun  deleteSubject(subject: Subject){
        subjects= subjects.toMutableList().also{
            it.remove(subject)
        }
    }

}