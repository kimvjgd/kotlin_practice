package com.dongpakka.recyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainData(val title:String, val content:String, val image: String): Parcelable

