package com.example.demomvp.services.resposes

import com.example.demomvp.base.BaseResponse
import com.example.demomvp.models.Food

data class FoodResponse(val data: MutableList<Food>) : BaseResponse()