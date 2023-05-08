package com.example.studhelper.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Group(
    var name: String,
    val code: String,
) : Parcelable

private val groupDB = listOf<Group>(  Group("P33131", "123213"))

private fun groups(): List<Group> {
    val groups = mutableListOf<Group>()
    for ((_, group) in groupDB.withIndex()) {
        groups.add(group)
    }
    return groups
}

object GroupsRepo {
    fun getGroups(): List<Group> = groups()
}