package com.ft.myapplication

enum class FlagType {
    None,
    British,
    American,
    Germany,
    Canada
}

fun flagToResourceId(flagType: FlagType): Int = when (flagType) {
        FlagType.American -> R.drawable.unitedstates
        FlagType.Canada -> R.drawable.canada
        FlagType.British -> R.drawable.unitedkingdom
        FlagType.Germany -> R.drawable.germany
        else -> -1
    }

