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
    val beenCountWt: Long,
    val dateWt: LocalDate,
    val titleWt: String,
    val imgWt: Uri?,
    val expWt: String
) : Parcelable