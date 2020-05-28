package com.ft.myapplication.view

interface StartView : BaseView {
    fun showConnectionTime_sec(time: Long)
    fun animateConnection(animate: Boolean)
    fun disconnected()
}