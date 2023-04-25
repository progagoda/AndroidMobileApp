package com.example.studhelper.utils

fun redirect(url: () -> Unit) {
    url()
}