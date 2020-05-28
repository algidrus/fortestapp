package com.ft.myapplication.presenter

import com.ft.myapplication.*
import com.ft.myapplication.model.ModelFacade
import com.ft.myapplication.view.SecondView

class SecondPresenter(private val view: SecondView) : BasePresenter {

    override val screen: ScreenType
        get() = ScreenType.Second

    private var model = ModelFacade.instance()

    fun done() {
        model.setSelectedFlag(FlagType.British)
        ScreenController.instance().screenAccomplished(
            screen,
            ScreenController.AccomplishedResult(
                ResultType.Positive
            )
        )
    }

    fun flagSelected(flagType: FlagType) {
        model.setSelectedFlag(flagType)

        ScreenController.instance().screenAccomplished(
            screen,
            ScreenController.AccomplishedResult(
                ResultType.Positive
            )
        )
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onStop() {
    }

    override fun onStart() {
    }
}