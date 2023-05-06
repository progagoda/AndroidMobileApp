package com.example.studhelper.data

import android.os.Parcelable
import com.example.studhelper.R
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Profile(
    val name: String,
    var group: Group,
    val avatar: Int = R.drawable.avatar_bad_breaking,
    val isu: String,
    val password: String,
    var admin: Boolean
) : Parcelable
private val profilesDB = listOf<Profile>()
private fun profiles(): List<Profile>{
    val profiles = mutableListOf<Profile>()
    for ((_,profile) in profilesDB.withIndex()){
        profiles.add(profile)
    }
    return profiles
}
object ProfilesRepo {
    fun getProfiles(): List<Profile> = profiles()
}
