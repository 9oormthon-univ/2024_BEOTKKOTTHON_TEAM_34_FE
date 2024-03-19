package com.goorm.kkiri.ui.home

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.navigation.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityHomeWritePostBinding

class HomeWritePostActivity : BaseActivity<ActivityHomeWritePostBinding>(R.layout.activity_home_write_post) {
    private val args by navArgs<HomeWritePostActivityArgs>()

    override fun setLayout() {
        binding.postType = args.postType
        setClickBackButton()
        setWriteCompleteButton()
        accessAlbum()
    }

    private fun setClickBackButton() {
        binding.toolbarHomeWritePost.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setWriteCompleteButton() {
        binding.btnPostWriteCompleteButton.setOnClickListener {
            finish()
        }
    }

    private fun accessAlbum() {
        binding.ibPostWriteCameraButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // 여러 이미지 선택 가능하도록 설정
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES_REQUEST)
        }
    }

    // onActivityResult 메서드에서 이미지 업로드 처리 전에 다음과 같이 이미지 수를 확인합니다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGES_REQUEST && resultCode == Activity.RESULT_OK) {
            var selectedImageCount = 0
            if (data != null) {
                if (data.clipData != null) { // 여러 이미지 선택
                    selectedImageCount = data.clipData!!.itemCount
                } else if (data.data != null) { // 하나의 이미지 선택
                    selectedImageCount = 1
                }
            }

            if (selectedImageCount > 0) {
                // 이미지 업로드 처리
                if (selectedImageCount <= MAX_IMAGES_UPLOAD) {
                    // 선택한 이미지의 수가 최대 허용치인 10장 이내인 경우에만 업로드 처리
                    uploadSelectedImages(data)
                } else {
                    // 선택한 이미지의 수가 최대 허용치를 초과한 경우에는 사용자에게 알림
                    Toast.makeText(this, "You can select up to $MAX_IMAGES_UPLOAD images.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uploadSelectedImages(data: Intent?) {
        // 선택한 이미지를 업로드하는 코드를 여기에 추가
        // 여러 이미지를 선택한 경우 data.clipData를 통해 이미지에 접근할 수 있습니다.
        // 하나의 이미지를 선택한 경우 data.data를 통해 이미지에 접근할 수 있습니다.
    }

    companion object {
        private const val MAX_IMAGES_UPLOAD = 10
        private const val PICK_IMAGES_REQUEST = 123
    }
}