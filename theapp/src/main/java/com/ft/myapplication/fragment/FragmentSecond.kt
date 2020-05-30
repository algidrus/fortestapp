package com.ft.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ft.myapplication.FlagType
import com.ft.myapplication.FlagsInfo
import com.ft.myapplication.R
import com.ft.myapplication.adapter.FlagsAdapter
import com.ft.myapplication.presenter.SecondPresenter
import com.ft.myapplication.view.SecondView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSecond.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSecond : Fragment(), SecondView, FlagsAdapter.ClickListener {
    private val presenter = SecondPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflated = inflater.inflate(R.layout.fragment_second, container, false)

        val rcview = inflated.findViewById(R.id.rcview) as RecyclerView
        val llm = LinearLayoutManager(activity)
        rcview.setLayoutManager(llm)
        rcview.setHasFixedSize(true)

        fillData()
        initAdapter(rcview)

        return inflated
    }

    private var flagsInfo: List<FlagsInfo>? = null
    private fun fillData() {
        flagsInfo = listOf(
            FlagsInfo(
                FlagType.Canada,
                "Canada",
                "some info about Canada"
            ),

            FlagsInfo(
                FlagType.American,
                "United States",
                "some info about US"
            ),

            FlagsInfo(
                FlagType.British,
                "United Kingdom",
                "some info UK"
            ),

            FlagsInfo(
                FlagType.Germany,
                "Germany",
                "some info about Germany"
            )
        )
    }

    private fun initAdapter(rcview: RecyclerView) {
        val adapter = FlagsAdapter(flagsInfo!!)
        adapter.setOnClickListener(this)
        rcview.setAdapter(adapter)
    }

    override fun onClick(flagType: FlagType) {
        presenter.flagSelected(flagType)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSecond().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
