package com.example.exoplayerdemo.bean


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class RespData(
    val p: List<Post>
):Parcelable

@Parcelize
data class Post(
    val at: Long,
    val att: Attachment,
    val count: List<Int>,
    val desc: String,
    val id: String,
    val is_favorite: Boolean,
    val is_prv: String,
//    val p_stk: List<Stakeholder>,
    val plc: List<String>,
    val private: @RawValue List<Any>,
    val reply: @RawValue List<Any>,
    val source: @RawValue List<Any>
) : Parcelable

@Parcelize
data class Attachment(
    val id: String,
    val l: Int,
    val p_ids: String,
    val type: String
) : Parcelable

@Parcelize
data class Stakeholder(
    val id: String,
    val details: @RawValue List<Any>
) : Parcelable