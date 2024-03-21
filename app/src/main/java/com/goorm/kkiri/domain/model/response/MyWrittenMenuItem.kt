package com.goorm.kkiri.domain.model.response

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize // 임시 데이터 타입
data class MyWrittenMenuItem(
    @SerializedName("id_wt")
    val recordIdWt: Long,
    var beenCountWt: Long,
    var dateWt: LocalDate,
    var titleWt: String,
    val imgWt: Uri?,
    var expWt: String
) : Parcelable