package com.example.exoplayerdemo.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PushNotificationData(
    val to: String?
) : Parcelable {

}