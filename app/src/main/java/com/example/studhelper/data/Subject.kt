package com.example.studhelper.data

import android.os.Parcelable
import com.example.studhelper.R
import kotlinx.android.parcel.Parcelize

//TODO добавить поле лист записавшихся студентов
//TODO изменить тип пол group на объект
@Parcelize
data class Subject(
    val name: String,
    val admin: Profile,
    var students: List<Profile>
) : Parcelable

private val subjectsDB = listOf<Subject>(
    Subject(
        "Методы и средства программной инженерии",
        Profile(
            "Артем Сергеевич",
            GroupsRepo.getGroups()[0],
            R.drawable.avatar_bad_breaking,
            "285384",
            "12345",
            true
        ),
        listOf<Profile>(
            Profile(
                "Артем Ваховскович",
                GroupsRepo.getGroups()[0],
                R.drawable.avatar_bad_breaking,
                "285381",
                "12345",
                false
            ),
            Profile(
                "Артем Крисанович",
                GroupsRepo.getGroups()[0],
                R.drawable.avatar_bad_breaking,
                "285382",
                "12345",
                false
            ),
            Profile(
                "Артем Шабалович",
                GroupsRepo.getGroups()[1],
                R.drawable.avatar_bad_breaking,
                "285383",
                "12345",
                false
            ),
            Profile(
                "Артем Бритковскович",
                GroupsRepo.getGroups()[1],
                R.drawable.avatar_bad_breaking,
                "285385",
                "12345",
                false
            ),
            Profile(
                "Артем Артемович",
                GroupsRepo.getGroups()[1],
                R.drawable.avatar_bad_breaking,
                "285386",
                "12345",
                false
            )
        )
    )    )


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
