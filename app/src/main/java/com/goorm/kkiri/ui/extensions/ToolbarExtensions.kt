package com.goorm.kkiri.ui.extensions

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.goorm.kkiri.ui.common.PostType

@BindingAdapter("setHelpTitle")
fun Toolbar.setHelpType(type: PostType) {
    title = when (type) {
        PostType.HelpMe -> "도와주세요"
        PostType.HelpYou -> "도와줄게요"
    }
}