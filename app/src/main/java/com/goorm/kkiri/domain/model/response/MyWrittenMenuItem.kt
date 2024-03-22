package com.goorm.kkiri.domain.model.response

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

data class MyWrittenMenuItem(
    val recordIdWt: Long,
    var beenCountWt: Long,
    var dateWt: LocalDate,
    var titleWt: String,
    val imgWt: Uri?,
    var expWt: String
)