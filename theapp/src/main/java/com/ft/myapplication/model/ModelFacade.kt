package com.ft.myapplication.model

import android.os.Handler
import android.os.Looper
import com.ft.myapplication.FlagType
import com.ft.myapplication.modelImpl.ConnectionModel
import com.ft.myapplication.modelImpl.FlagModel

class ModelFacade private constructor() : Model {
    private val connectionModel = ConnectionModel()
    private val flagModel = FlagModel()

    companion object : Handler(Looper.getMainLooper() ?: Looper.myLooper()) {
        private val singletonModel: ModelFacade by lazy {
            ModelFacade()
        }

        fun instance(): Model = singletonModel
    }

    override fun isConnecting(): Boolean = connectionModel.connecting
    override fun setConnectionState(state: Boolean) {
        connectionModel.connecting = state
    }

    override fun getConnectionStartTime(): Long = connectionModel.startTime
    override fun setConnectionStartTime(startTime: Long) {
        connectionModel.startTime = startTime
    }


    override fun getSelectedFlag(): FlagType = flagModel.selectedFlag
    override fun setSelectedFlag(flag: FlagType) {
        flagModel.selectedFlag = flag
    }
}