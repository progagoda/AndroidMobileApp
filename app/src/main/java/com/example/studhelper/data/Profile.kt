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
    var group: Group,
    val avatar: Int = R.drawable.avatar_bad_breaking,
    val isu: String,
    val password: String,
    var admin: Boolean
) : Parcelable
private val profilesDB = listOf<Profile>(
   Profile("Артем Сергеевич", GroupsRepo.getGroups()[0], R.drawable.avatar_bad_breaking, "285384", "12345",true ),
   Profile("Артем Ваховскович", GroupsRepo.getGroups()[0], R.drawable.avatar_bad_breaking, "285381", "12345",false),
   Profile("Артем Крисанович", GroupsRepo.getGroups()[0], R.drawable.avatar_bad_breaking, "285382", "12345",false),
   Profile("Артем Шабалович", GroupsRepo.getGroups()[2], R.drawable.avatar_bad_breaking, "285383", "12345",true),
   Profile("Артем Бритковскович", GroupsRepo.getGroups()[2], R.drawable.avatar_bad_breaking, "285385", "12345",false),
   Profile("Артем Артемович", GroupsRepo.getGroups()[2], R.drawable.avatar_bad_breaking, "285386", "12345",false),
)
private fun profiles(): List<Profile>{
    val profiles = mutableListOf<Profile>()
    for ((_,profile) in profilesDB.withIndex()){
        profiles.add(profile)
    }
    return profiles
}
object ProfilesRepo {
    fun getProfiles(): List<Profile> = profiles()
    fun addProfile(profile: Profile) {
        val profiles = profiles() as MutableList<Profile>
        profiles.add(profile)

    }
}

