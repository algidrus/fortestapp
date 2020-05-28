package com.ft.myapplication.presenter

import com.ft.myapplication.ScreenType

interface BasePresenter {
    val screen: ScreenType
    fun onPause()
    fun onResume()
    fun onStop()
    fun onStart()
}