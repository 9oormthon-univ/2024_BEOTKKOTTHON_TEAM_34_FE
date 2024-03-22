package com.goorm.kkiri.ui.mypage

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityDetailImWriteBinding
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.ui.home.HomeWritePostActivity
import com.goorm.kkiri.ui.mypage.adapter.IwViewPagerAdapter
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.O)
class DetailImWriteActivity :
    BaseActivity<ActivityDetailImWriteBinding>(R.layout.activity_detail_im_write),
    DetailImWriteDialogInterface {
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var menuItem: MyWrittenMenuItem
    private var localDate = ""
    private var imgUrl = "" //

    override fun setLayout() {
        initValue()
        setDetailPage()
        settingListClickEvent()
        onImageAttachedViewPager()
        resultBackToTheDetailImWrite()
    }
    private fun initValue(){
        menuItem = MyWrittenMenuItem( recordIdWt = 0L, // 기본값 설정
            beenCountWt = 0L, // intent에서 받아온 값으로 덮어쓸 예정
            dateWt = LocalDate.now(), // 적절한 기본값 설정
            titleWt = "", // 적절한 기본값 설정
            imgWt = null, // 적절한 기본값 설정
            expWt = ""
        ) // 적절한 기본값 설정
        menuItem.beenCountWt = intent.getLongExtra("helpBc",0L)
        localDate = intent.getStringExtra("helpDt").toString()
        menuItem.expWt = intent.getStringExtra("helpExp").toString()
        imgUrl = intent.getStringExtra("helpImg").toString()
        menuItem.titleWt = intent.getStringExtra("helpTt").toString()
    }
    private fun setDetailPage(){
        with(binding){
            tvMypageIwDtTitle.text = menuItem.titleWt
            tvMypageIwDtDate.text = localDate
            tvIwDtBeenCount.text = menuItem.beenCountWt.toString()
        }
    }



    // add 버튼, back 버튼 클릭 이벤트
    private fun settingListClickEvent() {

        with(binding) {

            vwIwDtInfo.isVisible = true
            iwAddMenu.setOnClickListener {
                vwIwDtInfo.isVisible = false
                addMenu()
            }
            iwWtBack.setOnClickListener {
                //수정 내용 가지고 뒤로가기
                finish()
            }
            iwWtBack.setOnClickListener {
                returnResult()
            }
        }

    }


    //add 버튼 상세 구현, 다이얼로그 띄우기
    private fun addMenu() {
        val dialog = DetailImWritDialog(
            this,
            "게시글 수정",
            "게시글 삭제",
            0
        ).apply {
            // 콜백 설정
            setOnDismissListener(object : DialogDismissListener {
                override fun onDialogDismissed() {
                    // 다이얼로그가 닫힐 때 실행할 로직
                    binding.vwIwDtInfo.isVisible = true
                }
            })
        }
        dialog.isCancelable = true
        dialog.showNow(supportFragmentManager, "DetailImWritDialog")

    }


    // 1번 edit, 2번 remove  다이얼로그 내용 각각 클릭 이벤트
    override fun onClickButton(id: Int) {
        when (id) {
            1 -> {
                openDetailActivity()
                binding.vwIwDtInfo.isVisible = true
            }  // 게시글 수정
            2 -> finish() // 게시글 삭제
        }

    }

    private fun openDetailActivity() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("helpTt", menuItem.titleWt)
            putExtra("helpDt", localDate)
            putExtra("helpImg", menuItem.imgWt)
            putExtra("helpExp", menuItem.expWt)
            putExtra("helpBc", menuItem.beenCountWt.toString())
        }
        startForResult.launch(intent)
    }

    //이미지와 뷰페이저 연결
    private fun onImageAttachedViewPager() {

        with(binding) {
            // IwViewPagerAdapter 인스턴스 생성
            val iwViewPagerAdapter = IwViewPagerAdapter(this@DetailImWriteActivity)

            // ViewPager2에 어댑터 설정
            iwViewPager.adapter = iwViewPagerAdapter

            iwViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    // 페이지가 스크롤될 때 호출
                }

                override fun onPageSelected(position: Int) {
                    updateIndicators(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    // 페이지 스크롤 상태가 변경될 때 호출
                }
            })

        }
    }

    //인디케이터에 이미지 세팅
    private fun setIndicator(imgView: ImageView, drawable: Drawable?) {
        imgView.setImageDrawable(drawable)
    }


    //인디케이터 업데이트
    private fun updateIndicators(selectedPosition: Int) {
        val defaultDrawable = AppCompatResources.getDrawable(
            this@DetailImWriteActivity,
            R.drawable.shape_indicator_gray_12dp
        )
        val selectedDrawable = AppCompatResources.getDrawable(
            this@DetailImWriteActivity,
            R.drawable.shape_indicator_white_12dp
        )

        val indicators = listOf(
            binding.indicator0IvMain,
            binding.indicator1IvMain,
            binding.indicator2IvMain,
        )

        indicators.forEachIndexed { index, imageView ->
            setIndicator(
                imageView,
                if (index == selectedPosition) selectedDrawable else defaultDrawable
            )
        }
    }

    //받은데이터처리
    private fun resultBackToTheDetailImWrite(){
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 결과 처리
                val data: Intent? = result.data
                // 데이터 사용, 예: data.getStringExtra("resultKey")
                if (data != null) {
                    menuItem.titleWt = data.getStringExtra("titleResult").toString()
                    menuItem.beenCountWt = data.getLongExtra("BeenCountResult",menuItem.beenCountWt)
                    menuItem.expWt = data.getStringExtra("ExpResult").toString()

                    with(binding){
                        tvMypageIwDtTitle.text = menuItem.titleWt
                        tvIwDtBeenCount.text = menuItem.beenCountWt.toString()
                        tvMypageIwDtExp.text = menuItem.expWt
                        //url
                        //date
                        //nickname
                    }

                }
            }
        }
    }

    //help, helped 로 돌려주기
    private fun returnResult() {
        val returnIntent = Intent()
        with(binding) {
            returnIntent.putExtra("rtHelpTt",tvMypageIwDtTitle.text.toString())
            returnIntent.putExtra("rtHelpDt", tvMypageIwDtDate.text.toString())
            returnIntent.putExtra("rtHelpImg", menuItem.imgWt).toString()
            returnIntent.putExtra("rtHelpExp", tvMypageIwDtExp.text.toString())
            returnIntent.putExtra("rtHelpBc", tvIwDtBeenCount.text.toString())
            returnIntent.putExtra("return", "ok")
        }
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }


}