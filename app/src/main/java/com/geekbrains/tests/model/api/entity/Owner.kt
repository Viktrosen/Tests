package com.geekbrains.tests.model.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    @Expose
    val login: String
)