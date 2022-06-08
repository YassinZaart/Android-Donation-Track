package com.yaslau.myapplication.data

data class PostData(
    val address: String,
    val description: String,
    val phone_number: String,
    val time_created: String,
    val name: String,
    val charity_name: String,
    val value: Int,
    val contributions: Int,
    val id: Int
)