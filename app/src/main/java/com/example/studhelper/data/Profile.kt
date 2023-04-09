package com.example.studhelper.data

import android.os.Parcelable
import com.example.studhelper.R
import com.example.studhelper.data.ProfilesRepo.getProfiles
import kotlinx.android.parcel.Parcelize
//TODO добавить поле admin true false
//TODO создать отдельно объект группа, поля в нем названия и код и изменить поле group в Profile на тип объект
@Parcelize
 data class Profile(
    val name: String,
    var group: String = "",
    val avatar: Int = R.drawable.avatar_bad_breaking,
    val isu: String,
    val password: String,
) : Parcelable
private val profilesDB = listOf<Profile>(
   Profile("Артем Сергеевич", "P33131", R.drawable.avatar_bad_breaking, "285384", "12345" ),
   Profile("Артем Ваховскович", "P33131", R.drawable.avatar_bad_breaking, "285381", "12345"),
   Profile("Артем Крисанович", "P33131", R.drawable.avatar_bad_breaking, "285382", "12345"),
   Profile("Артем Шабалович", "P33131", R.drawable.avatar_bad_breaking, "285383", "12345"),
   Profile("Артем Бритковскович", "P33131", R.drawable.avatar_bad_breaking, "285385", "12345"),
   Profile("Артем Артемович", "P33131", R.drawable.avatar_bad_breaking, "285386", "12345"),
)
private fun profiles(): List<Profile>{
    val profiles = mutableListOf<Profile>()
    for ((index,profile) in profilesDB.withIndex()){
        profiles.add(profile)
    }
    return profiles
}
object ProfilesRepo {
    fun getProfiles(): List<Profile> = profiles()
    fun addProfile(profile: Profile) {
        var profiles = mutableListOf<Profile>()
        profiles = profiles() as MutableList<Profile>
        profiles.add(profile)

    }

    fun setGroup(profile: Profile, group: String) {
        profile.group = group;
    }
}

