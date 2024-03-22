package com.goorm.kkiri.domain.model.response

import android.net.Uri
import androidx.lifecycle.MutableLiveData

data class MyImageItem(
    val imgId: Long,
    val imgUrl: Uri?,
    val imgCount: String?
)
