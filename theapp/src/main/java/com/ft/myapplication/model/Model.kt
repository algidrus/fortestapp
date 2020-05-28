package com.ft.myapplication.model

import com.ft.myapplication.FlagType

interface Model {
    fun isConnecting(): Boolean
    fun setConnectionState(state: Boolean)
    fun setConnectionStartTime(startTime: Long)
    fun getConnectionStartTime(): Long

    fun getSelectedFlag(): FlagType
    fun setSelectedFlag(flag: FlagType)
}