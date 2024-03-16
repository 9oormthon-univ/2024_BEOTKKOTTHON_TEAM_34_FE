package com.goorm.kkiri.domain.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class MyWrittenMenuItem(
    @SerializedName("id")
    val recordId: Long,
    val type: String,
    val image: String,
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updateAt: String
) : Parcelable