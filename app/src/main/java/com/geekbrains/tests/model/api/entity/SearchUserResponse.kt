package com.geekbrains.tests.model.api.entity

import com.google.gson.annotations.SerializedName


data class SearchUserResponse(

    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("items") var items: List<Items>
)