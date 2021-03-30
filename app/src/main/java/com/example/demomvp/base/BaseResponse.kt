package com.example.demomvp.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("error") internal var error: Int? = null
    @SerializedName("status") internal var status: Int? = null
    @SerializedName("message") internal var message: String? = null
}