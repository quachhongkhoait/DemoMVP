package com.example.demomvp.base

interface BasePresenter<VIEW> {
    fun onStart()
    fun setView(view: VIEW?)
}