package com.example.demomvp.base

interface BasePresenter<VIEW> {
    fun onStart()
    fun onStop()
    fun setView(view: VIEW?)
}