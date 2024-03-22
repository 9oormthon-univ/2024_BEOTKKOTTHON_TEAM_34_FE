package com.goorm.kkiri.ui.mypage

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.ActivityDetailBinding
import com.goorm.kkiri.domain.model.response.MyImageItem
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyImageButtonAdapter

@RequiresApi(Build.VERSION_CODES.O)
class DetailActivity :
    BaseActivity<ActivityDetailBinding>(R.layout.activity_detail), MenuClickListener {

    override fun setLayout() {
        initValue()
        returnResult()
        initAdapter()
    }

    private fun initValue() {
        with(binding) {
            etDtInputPostTitle.setText(intent.getStringExtra("helpTt"))
            etDtInputWritePostNeedBeans.setText(intent.getStringExtra("helpBc").toString())
            etDtInputPostWriteContent.setText(intent.getStringExtra("helpExp"))
        }
    }

    private fun returnResult() {
        with(binding) {
            btnPostWriteCompleteButton.setOnClickListener {
                val returnIntent = Intent()
                returnIntent.putExtra("titleResult", etDtInputPostTitle.text.toString())
                returnIntent.putExtra(
                    "BeenCountResult",
                    etDtInputWritePostNeedBeans.text.toString().toLongOrNull() ?: 0L
                )
                returnIntent.putExtra("ExpResult", etDtInputPostWriteContent.text.toString())
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }

    private fun accessAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // 여러 이미지 선택 가능하도록 설정
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES_REQUEST)
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
                    if(selectedImageCount < MAX_IMAGES_UPLOAD){
                        DataSource.pictureData.add(0,
                            MyImageItem(1, DataSource.uri,"${DataSource.pictureData.size}")
                        )
                    }
                    updateRecyclerView()

                }
                else {
                    // 선택한 이미지의 수가 최대 허용치를 초과한 경우에는 사용자에게 알림
                    Toast.makeText(
                        this,
                        "You can select up to $MAX_IMAGES_UPLOAD images.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun uploadSelectedImages(data: Intent?) {
        data?.let {
            // 기존 선택된 이미지 목록을 초기화
            DataSource.pictureData.clear()

            // 새로운 이미지 목록을 생성
            val newImages = mutableListOf<MyImageItem>()

            // 선택한 이미지가 여러 개인 경우
            if (it.clipData != null) {
                val clipData = it.clipData!!
                for (i in 0 until clipData.itemCount) {
                    val imageUri = clipData.getItemAt(i).uri
                    newImages.add(MyImageItem(DataSource.pictureData.size.toLong(), imageUri, (i+1).toString()))
                }
            }
            // 선택한 이미지가 하나인 경우
            else if (it.data != null) {
                val imageUri = it.data!!
                newImages.add(MyImageItem(DataSource.pictureData.size.toLong(), imageUri,  DataSource.pictureData.size.toString()))
            }

            // 새로운 이미지 목록으로 DataSource를 업데이트
            DataSource.pictureData.addAll(newImages)

            // 어댑터와 리사이클러뷰 업데이트
            updateRecyclerView()
        }
    }



    companion object {
        private const val MAX_IMAGES_UPLOAD = 10
        private const val PICK_IMAGES_REQUEST = 123
    }

    private fun initAdapter() {
        val adapter = MyImageButtonAdapter(this)
        DataSource.initPicturesData()
        adapter.update(DataSource.pictureData)
        binding.rvDtUploadImageFileList.adapter = adapter
    }

    override fun menuClickListener(position: Long) {
        Log.d("클릭", "클릭")
        accessAlbum()
    }

    private fun updateRecyclerView() {
        (binding.rvDtUploadImageFileList.adapter as? MyImageButtonAdapter)?.update(DataSource.pictureData)
    }

}