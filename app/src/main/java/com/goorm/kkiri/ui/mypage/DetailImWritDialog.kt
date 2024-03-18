package com.goorm.kkiri.ui.mypage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.goorm.kkiri.R
import com.goorm.kkiri.databinding.DialogMyWrittenEditRemoveBinding

class DetailImWritDialog(
    private val detailImWriteDialogInterface: DetailImWriteDialogInterface,
    private val tvEdit: String,
    private val tvRemove: String,
    private val id: Int
) : DialogFragment() {

    private var _binding: DialogMyWrittenEditRemoveBinding? = null
    private val binding get() = _binding!!

    //다이얼로그의 크기를 부모의 뷰에 맞추기위한 세팅
    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        window?.setLayout(width, height) // 다이얼로그의 너비와 높이 설정
        window?.setGravity(Gravity.BOTTOM) // 필요한 경우 다이얼로그의 위치 조정
    }

    //다이얼로그 바인딩
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogMyWrittenEditRemoveBinding.inflate(inflater, container, false)
        setupDialogWindow()
        return binding.root
    }


    //다이얼로그 설정 세팅
    private fun setupDialogWindow() {
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.apply {
                //백그라운드 투명
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                //너비는 match_parent, 높이는 wrap_content
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //아래 쪽에 부착
                setGravity(Gravity.BOTTOM)
                //서서히 올라오는 스타일 지정
                attributes?.windowAnimations = R.style.DialogAnimation
            }
        }
    }

    //클릭리스너 등록
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    //각각 버튼에 클릭 리스너 등록
    private fun setupClickListeners() {
        binding.tvMyWrittenEdit.apply {
            text = tvEdit
            setOnClickListener { onButtonClicked(1) }
        }

        binding.tvMyWrittenRemove.apply {
            text = tvRemove
            setOnClickListener { onButtonClicked(2) }
        }
    }

    //버튼 클릭시 이벤트
    private fun onButtonClicked(buttonId: Int) {
        detailImWriteDialogInterface.onClickButton(buttonId)
        dismiss()
    }

    //화면 사라지면 바인딩 제거
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
