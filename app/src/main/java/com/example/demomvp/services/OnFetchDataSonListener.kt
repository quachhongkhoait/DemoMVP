package com.example.demomvp.services

interface OnFetchDataSonListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}