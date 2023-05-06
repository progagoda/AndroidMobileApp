package com.example.studhelper.data

import android.os.Parcelable
import com.example.studhelper.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject(
    val name: String,
    val admin: Profile,
    var students: List<Profile>
) : Parcelable

private val subjectsDB = listOf<Subject>()


private fun subjects(): List<Subject> {
        val subjects = mutableListOf<Subject>()
        for ((_, subject) in subjectsDB.withIndex()) {
            subjects.add(subject)
        }
        return subjects
    }
    object SubjectsRepo {
        fun getSubjects(): List<Subject> = subjects()
    }
