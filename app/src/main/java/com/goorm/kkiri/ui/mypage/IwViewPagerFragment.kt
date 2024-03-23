package com.goorm.kkiri.ui.mypage
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentIwViewPagerBinding

class IwViewPagerFragment : BaseFragment<FragmentIwViewPagerBinding>(R.layout.fragment_iw_view_pager) {

    override fun setLayout() {
        arguments?.let {
            val imgDrawableResId = it.getInt(ARG_IMG_DRAWABLE)
            val drawable = ContextCompat.getDrawable(requireContext(), imgDrawableResId)
            binding.ivIwImage.setImageDrawable(drawable)
        }
    }

    companion object {
        private const val ARG_IMG_DRAWABLE = "imageDrawable"

        fun newInstance(imgDrawableResId: Int): IwViewPagerFragment {
            val fragment = IwViewPagerFragment()
            val args = Bundle().apply {
                putInt(ARG_IMG_DRAWABLE, imgDrawableResId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
