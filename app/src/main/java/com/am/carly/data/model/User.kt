package com.am.carly.data.model

import com.google.firebase.Timestamp

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var image: String = "",
    var joinedDate: Timestamp? = null,
    var rating: Int = 0,
    var isUserApproved: Boolean = false
)


