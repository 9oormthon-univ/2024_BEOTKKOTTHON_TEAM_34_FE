package com.goorm.kkiri.ui.account

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    override fun setLayout() {
        setClickListener()
        setupEditTexts()
        setupTextWatchers()
    }

    // 클릭 리스너 지정, 회원가입 완료 -> 로그인 화면, 포커스 지정
    private fun setClickListener() {
        with(binding) {
            btNextRegister.setOnClickListener {
                navigateToDestination(RegisterFragmentDirections.actionNavigationRegisterToNavigationSignIn())
            }
            setRequestFocusOnClick(etNicknameField)
            setRequestFocusOnClick(etIdField)
            setRequestFocusOnClick(etPwField)
            setRequestFocusOnClick(etDoubleCheckedPwField)
        }
    }

    // 포커스 이동 설정
    private fun setupEditTexts() {
        with(binding) {
            setOnNextFocus(etNicknameField, etIdField)
            setOnNextFocus(etIdField, etPwField)
            setOnNextFocus(etPwField, etDoubleCheckedPwField)
            setOnDoneHideKeyboard(etDoubleCheckedPwField)
        }
    }

    // 포커스 요청 설정
    private fun setRequestFocusOnClick(editText: EditText) {
        editText.setOnClickListener { it.requestFocus() }
    }

    // 입력 후 엔터를 누르면 다음 EditText로 포커스 이동
    private fun setOnNextFocus(currentEditText: EditText, nextEditText: EditText) {
        currentEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                nextEditText.requestFocus()
                true
            } else {
                false
            }
        }
    }

    // 마지막에 가면 키보드를 숨김
    private fun setOnDoneHideKeyboard(editText: EditText) {
        editText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(v)
                true
            } else {
                false
            }
        }
    }

    // 키보드를 숨기는 함수
    private fun hideKeyboard(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // 텍스트 변경 감지를 위한 TextWatcher 설정
    private fun setupTextWatcher(editText: EditText, textView: TextView, message: String) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = message
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // 비밀번호 일치 여부 확인을 위한 TextWatcher 설정
    private fun setupPasswordTextWatchers() {
        val passwordWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                with(binding) {
                    val password = etPwField.text.toString()
                    val confirmPassword = etDoubleCheckedPwField.text.toString()

                    if (password.isEmpty() && confirmPassword.isEmpty()) {
                        icChecked.visibility = View.GONE
                        tvDoubleCheckedPassword.text = ""
                    } else if (password == confirmPassword) {
                        icChecked.visibility = View.VISIBLE
                        icChecked.setImageResource(R.drawable.ic_checked)
                        tvDoubleCheckedPassword.text = "비밀번호 일치"
                        tvDoubleCheckedPassword.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.blue_main
                            )
                        )
                    } else {
                        icChecked.visibility = View.VISIBLE
                        icChecked.setImageResource(R.drawable.ic_checked_fail)
                        tvDoubleCheckedPassword.text = "비밀번호 불일치"
                        tvDoubleCheckedPassword.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.failed_color
                            )
                        )
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etPwField.addTextChangedListener(passwordWatcher)
        binding.etDoubleCheckedPwField.addTextChangedListener(passwordWatcher)
    }

    // 텍스트 감지 설정
    private fun setupTextWatchers() {
        with(binding) {
            setupTextWatcher(etNicknameField, tvEnabledNick, "사용 가능한 닉네임입니다.")
            setupTextWatcher(etIdField, tvEnabledId, "사용 가능한 아이디입니다.")
            setupPasswordTextWatchers()
        }
    }

    // 네비게이션 이동
    private fun navigateToDestination(action: NavDirections) {
        findNavController().navigate(action)
    }
}
