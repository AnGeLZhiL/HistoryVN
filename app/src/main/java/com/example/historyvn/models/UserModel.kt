package com.example.historyvn.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val midlleName: String,
    val login: String,
    val password: String,
    val role: Role,
    val picture: String,
    val rating: Double
)

@Serializable
enum class Role {
    ADMIN, USER
}

@Serializable
data class UserInfoModel(
    val lastName: String,
    val firstName: String,
    val midlleName: String,
    val picture: String,
    val rating: Double,
    val id: Int
)

@Serializable
data class UserTestsModel(
    val current: Double,
    val testTitle: String
)

@Serializable
data class UserRegisterModel(
    val lastName: String,
    val firstName: String,
    val midlleName: String,
    val login: String,
    val password: String,
    val picture: Int?,
)