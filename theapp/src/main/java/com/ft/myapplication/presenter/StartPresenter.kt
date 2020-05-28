package com.ft.myapplication.presenter

import android.os.Handler
import android.os.Looper
import com.ft.myapplication.ResultType
import com.ft.myapplication.ScreenController
import com.ft.myapplication.ScreenType
import com.ft.myapplication.flagToResourceId
import com.ft.myapplication.model.ModelFacade
import com.ft.myapplication.view.StartView
import java.util.*

// for command and multiproject work
import com.ft.somesubproject.OtherClass


class StartPresenter(private val view: StartView)
    : Handler(Looper.getMainLooper() ?: Looper.myLooper()),
    BasePresenter {

    private var model = ModelFacade.instance()
    private var timer: Timer? = null

    override val screen: ScreenType
        get() = ScreenType.Start

    // ****** special implemenation to show work with projects of other teams
    val otherClass = OtherClass()

    fun connect() {

        post {
            model.setConnectionStartTime(System.currentTimeMillis())
            val connectState = !model.isConnecting()
            model.setConnectionState(connectState)
            val connectState2 = model.isConnecting()

            when (connectState2) {
                false -> runDisconnecting()
                else -> runConnecting()
            }
        }
    }

    private fun runConnecting() {
        // start timer
        view.animateConnection(model.isConnecting())

        when(timer) {
            null -> timer = Timer()
            else -> timer?.cancel()
        }

        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                post{
                    val timeAlignToSec = ((System.currentTimeMillis() - model.getConnectionStartTime())/1000L).toLong()
                    view.showConnectionTime_sec(timeAlignToSec)
                }
            }
        }, 0, 1000)
    }

    private fun runDisconnecting() {
        // stop timer
        timer?.cancel()
        timer = null

        view.animateConnection(model.isConnecting())
        view.disconnected()
    }

    fun exit() {
        ScreenController.instance().screenAccomplished(
            screen,
            ScreenController.AccomplishedResult(
                ResultType.Finish
            )
        )
    }

    fun done() {
        ScreenController.instance().screenAccomplished(
            screen,
            ScreenController.AccomplishedResult(
                ResultType.Positive
            )
        )
    }

    fun getIconId(): Int {
        val flagId = model.getSelectedFlag()
        val id = flagToResourceId(flagId)

        return when {
            (id<0) -> android.R.drawable.ic_menu_add
            else -> id
        }
    }

    override fun onPause() {
        post {
            runDisconnecting()
        }
    }

    override fun onResume() {
        post {
            // restore our previous state
            when (model.isConnecting()) {
                true -> runConnecting()
                else -> {

                }
            }
        }
    }

    override fun onStop() {
    }

    override fun onStart() {
    }
}