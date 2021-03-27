package com.example.androidhomework.contacts.data

data class Contact(
    val id: Long,
    val name: String,
    val number: List<String>,
    val mail: List<String>
)