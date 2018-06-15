package io.sheltek.intellij.drone.model

data class Result(
        val gender: String,
        val name: Name,
        val location: Location,
        val email: String,
        val login: Login,
        val dob: String,
        val registered: String,
        val phone: String,
        val cell: String,
        val picture: Picture,
        val nat: String
)