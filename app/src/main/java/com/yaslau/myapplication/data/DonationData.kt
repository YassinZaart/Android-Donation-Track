package com.yaslau.myapplication.data

data class DonationData(
    val date: String,
    val donee_id: String,
    val type: String,
    val user_name: String,
    val value: Int
)