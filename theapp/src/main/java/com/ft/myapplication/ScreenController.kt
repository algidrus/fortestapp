package com.ft.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ft.myapplication.fragment.FragmentSecond
import com.ft.myapplication.fragment.FragmentStart


class ScreenController private constructor(var activity: AppCompatActivity) {

    data class AccomplishedResult(
        val result: ResultType
    )

    companion object {

        private var screenCtrl: ScreenController? = null

        fun initialize(activity: AppCompatActivity) {
            if (screenCtrl == null) {
                screenCtrl = ScreenController(activity)
            }

            instance().setScreen(ScreenType.Start)
        }

        fun restore(activity: AppCompatActivity) {

        }

        fun instance(): ScreenController {
            if (screenCtrl == null) {
                throw IllegalAccessException("Initialize ScreenController first!")
            }

            return screenCtrl!!
        }
    }

    private fun restore(activity_: AppCompatActivity) {
        activity = activity_
    }

    private fun setScreen(screen: ScreenType) {
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
//        transaction.setCustomAnimations(R.anim.show_fragment, R.anim.hide_fragment)

        when (screen) {
            ScreenType.None -> transaction.add(R.id.main_activity_layout, FragmentStart(), "")
            ScreenType.Second -> transaction.replace(R.id.main_activity_layout, FragmentSecond(), "")
            ScreenType.Start -> transaction.replace(R.id.main_activity_layout, FragmentStart(), "")
        }

        transaction.commitAllowingStateLoss()
    }

    fun screenAccomplished(screen: ScreenType, result: AccomplishedResult) {
        when (screen) {
            ScreenType.Start -> {
                when (result.result) {
                    ResultType.Positive -> setScreen(ScreenType.Second)
                    ResultType.Negative -> {

                    }
                    ResultType.Finish -> activity.finish()
                    else -> {

                    }
                }
            }

            ScreenType.Second -> {
                when (result.result) {
                    ResultType.Positive -> setScreen(ScreenType.Start)
                    ResultType.Negative -> {

                    }
                    ResultType.Finish -> activity.finish()
                    else -> {

                    }
                }
            }

            else -> {
                throw IllegalArgumentException("Unknown type of screen! Pls, check your screen switching logic.")
            }
        }
    }
}

