package com.ft.myapplication.adaptor

import android.R.attr.bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ft.myapplication.FlagType
import com.ft.myapplication.FlagsInfo
import com.ft.myapplication.R
import com.ft.myapplication.flagToResourceId


class FlagsAdapter internal constructor(flags_: List<FlagsInfo>) :
    RecyclerView.Adapter<FlagsAdapter.FlagsViewHolder>() {

    class FlagsViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {
        internal var cardView: CardView
        internal var flagImg: ImageView
        internal var countryName: TextView
        internal var info: TextView

        init {
            cardView = itemView.findViewById(R.id.card_view) as CardView
            countryName = itemView.findViewById(R.id.country_name)
            info = itemView.findViewById(R.id.info)
            flagImg = itemView.findViewById(R.id.flag_img) as ImageView
        }
    }


    interface ClickListener {
        fun onClick(flagType: FlagType)
    }

    private var flags: List<FlagsInfo> = emptyList()
    private var clickListener: ClickListener? = null

    fun setOnClickListener(clickListener_: ClickListener) {
        clickListener = clickListener_
    }

    private fun fireOnClick(flagType: FlagType) {
        clickListener?.let {
            it.onClick(flagType)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): FlagsViewHolder {
        val v: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.recycle_view_item, viewGroup, false)

        v.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                v?.let {
                    fireOnClick(v.tag as FlagType)
                }
            }
        })
        return FlagsViewHolder(v)
    }

    override fun onBindViewHolder(
        flagsViewHolder: FlagsViewHolder,
        i: Int
    ) {
        flagsViewHolder.countryName.setText(flags[i].countryName)
        flagsViewHolder.info.setText(flags[i].info)
        val flagId = flagToResourceId(flags[i].flag)

        val resource = flagsViewHolder.flagImg.context.resources
        val bmp = BitmapFactory.decodeResource(resource, flagId)
        val roundedBitmapDrawable: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resource, bmp)
        val roundPx = bmp.getWidth().toFloat() * 0.1f
        roundedBitmapDrawable.cornerRadius = roundPx
//        roundedBitmapDrawable.isCircular = true
        flagsViewHolder.flagImg.setImageDrawable(roundedBitmapDrawable)
        flagsViewHolder.itemView.tag = flags[i].flag
    }

    override fun getItemCount(): Int {
        return flags.size
    }

    init {
        this.flags = flags_
    }
}