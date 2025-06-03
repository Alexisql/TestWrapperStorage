package com.alexis.testwrapperstorage.domain.model

data class User(
    val name: String = "",
    val age: Int = 0,
    val email: String = ""
) {
    companion object {
        val defaultUser = User()
    }
}
