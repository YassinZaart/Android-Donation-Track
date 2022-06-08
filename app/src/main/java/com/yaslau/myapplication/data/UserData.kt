package com.yaslau.myapplication.data

data class UserData(
    val is_verified: Boolean,
    val email: String,
    val phone_number: String,
    val is_admin: Boolean,
    val name: String,
    val address: String,
    val description: String
)