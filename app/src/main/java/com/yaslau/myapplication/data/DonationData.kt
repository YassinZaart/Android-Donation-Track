package com.yaslau.myapplication.data

data class DonationData(
    val donation_id: Int,
    val description: String,
    val date: String,
    val donee_id: String,
    val name: String,
    val user_name: String,
    val value: Int
)