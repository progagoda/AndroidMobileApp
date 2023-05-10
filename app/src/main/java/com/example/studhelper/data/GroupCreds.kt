package com.example.studhelper.data

data class GroupCreds(
    val groupId: Int,
    val groupName: String,
    val inviteCode: String? = null
)
