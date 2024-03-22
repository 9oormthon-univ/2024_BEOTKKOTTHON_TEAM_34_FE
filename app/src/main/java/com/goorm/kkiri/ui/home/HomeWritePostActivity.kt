package com.goorm.kkiri.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityHomeWritePostBinding
import com.goorm.kkiri.ui.home.adapter.HomeUploadImageFileAdapter

class HomeWritePostActivity : BaseActivity<ActivityHomeWritePostBinding>(R.layout.activity_home_write_post) {
    private val args by navArgs<HomeWritePostActivityArgs>()
    private val adapter = HomeUploadImageFileAdapter()

    override fun setLayout() {
        binding.postType = args.postType
        setClickListener()
        setOutsideTouchListener()

    }

    private fun setClickListener() {
        setClickBackButton()
        accessAlbum()
        setWriteCompleteButton()
        clickInfo()
    }
    private fun clickInfo() {
        binding.btExchangeBeansValue.setOnClickListener {
            // tvInfoExchange가 서서히 나타남
            binding.tvInfoExchange.apply {
                alpha = 0f
                isVisible = true
                animate().alpha(1f).setDuration(300).setListener(null)
            }
        }
    }
    private fun setOutsideTouchListener() {
        binding.root.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isViewInBounds(binding.tvInfoExchange, event.rawX.toInt(), event.rawY.toInt())) {
                        // tvInfoExchange가 서서히 사라짐
                        binding.tvInfoExchange.animate().alpha(0f).setDuration(300).withEndAction {
                            binding.tvInfoExchange.isVisible = false
                        }
                        v.performClick() // 클릭으로 간주되는 경우 performClick을 호출
                    }
                }
            }
            true // 이벤트를 처리했음을 나타냅니다.
        }
        }



    private fun isViewInBounds(view: View, x: Int, y: Int): Boolean {
        val outRect = Rect()
        val location = IntArray(2)
        view.getDrawingRect(outRect)
        view.getLocationOnScreen(location)
        outRect.offset(location[0], location[1])
        return outRect.contains(x, y)
    }

    private fun initAdapter() {
        binding.rvUploadImageFileList.adapter = adapter
        binding.rvUploadImageFileList.visibility = View.VISIBLE
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
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    var selectedImageCount = 0
                    if (it.data != null) {
                        if (it.data?.clipData != null) {
                            selectedImageCount = it.data!!.clipData!!.itemCount
                        } else if (it.data?.data != null) {
                            selectedImageCount = 1
                        }
                    }
                    if (selectedImageCount > 0) {
                        // 이미지 업로드 처리
                        if (selectedImageCount <= MAX_IMAGES_UPLOAD) {
                            // 선택한 이미지의 수가 최대 허용치인 10장 이내인 경우에만 업로드 처리
                            uploadSelectedImages(it.data)
                        } else {
                            // 선택한 이미지의 수가 최대 허용치를 초과한 경우에는 사용자에게 알림
                            Toast.makeText(this, "You can select up to $MAX_IMAGES_UPLOAD images.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            //startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES_REQUEST)
            initAdapter()
        }
    }

    // onActivityResult 메서드에서 이미지 업로드 처리 전에 다음과 같이 이미지 수를 확인합니다.
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    }*/

    private fun uploadSelectedImages(data: Intent?) {
        // 선택한 이미지를 업로드하는 코드를 여기에 추가
        // 여러 이미지를 선택한 경우 data.clipData를 통해 이미지에 접근할 수 있습니다.
        // 하나의 이미지를 선택한 경우 data.data를 통해 이미지에 접근할 수 있습니다.
        val imageList = mutableListOf<String>()
        data?.clipData?.let { clipData ->
            for (i in 0..clipData.itemCount) {
                val imageUri = clipData.getItemAt(i).uri.toString()
                imageList.add(imageUri)
            }
        }
        adapter.update(imageList)
    }

    companion object {
        private const val MAX_IMAGES_UPLOAD = 10
        private const val PICK_IMAGES_REQUEST = 123
    }
}