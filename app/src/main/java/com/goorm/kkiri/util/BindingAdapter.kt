package com.goorm.kkiri.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.goorm.kkiri.R

object BindingAdapters {
//제거예정
    @JvmStatic
    @BindingAdapter("imageUri")
    fun setImageUri(view: ImageView, uri: Uri?) {
        if (uri != null) {
            view.setImageURI(uri)
        } else {
            // URI가 null일 때 기본 이미지 설정 (임시)
            view.setImageResource(R.mipmap.image_iw_sample)
        }
    }
}
