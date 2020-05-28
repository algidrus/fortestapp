package com.ft.myapplication.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.ft.myapplication.FlagType
import com.ft.myapplication.R
import com.ft.myapplication.flagToResourceId
import com.ft.myapplication.presenter.StartPresenter
import com.ft.myapplication.view.StartView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentStart.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentStart : Fragment(), StartView {
//    private var param1: String? = null
//    private var param2: String? = null
    private val presenter = StartPresenter(this)
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflated = inflater.inflate(R.layout.fragment_start, container, false)

        val btnConnect = inflated.findViewById<Button>(R.id.btn_connect)
        btnConnect?.let {
            it.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    presenter.connect()
                }
            })
        }

        // Inflate the layout for this fragment
        return inflated
    }

    override fun animateConnection(animate: Boolean) {
        val btnConnect = activity?.findViewById<Button>(R.id.btn_connect)
        btnConnect?.let {
            when (animate) {
                true -> {
                    val animation = AnimationUtils.loadAnimation(context, R.anim.pulse_animation)
                    it.startAnimation(animation)
                }
                else -> {
                    it.clearAnimation()
                }
            }
        }
    }

    override fun disconnected() {
        val btnConnect = activity?.findViewById<Button>(R.id.btn_connect)
        btnConnect?.setText(resources.getString(R.string.connect))
    }

    override fun showConnectionTime_sec(time: Long) {
        val btnConnect = activity?.findViewById<Button>(R.id.btn_connect)
        btnConnect?.let {
            it.setText(resources.getString(R.string.connect_fmt, time))
        }
    }

    override fun onCreateOptionsMenu(menu_: Menu, inflater: MenuInflater) {
        val inflated = inflater.inflate(R.menu.start_menu, menu_)
        menu = menu_
        super.onCreateOptionsMenu(menu_, inflater)

        menu?.getItem(0)?.let {
            var iconId = presenter.getIconId()
            it.setIcon(iconId)
            activity?.invalidateOptionsMenu()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_second_screen -> presenter.done()
            R.id.menu_exit -> presenter.exit()
        }
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentstart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentStart().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
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
