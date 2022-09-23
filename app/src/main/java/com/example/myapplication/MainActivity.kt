package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.PopupBoardMoreMenuBinding
import com.skydoves.balloon.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingPopUp: PopupBoardMoreMenuBinding

    private val balloon: Balloon by lazy{
        Balloon.Builder(this)
            .setIsVisibleOverlay(true) // comment this line and project will work but without overlay
            .setOverlayColorResource(R.color.purple_200)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            .setDismissWhenTouchOutside(false)
            .setDismissWhenShowAgain(false)
            .setDismissWhenOverlayClicked(false)
            .setLifecycleOwner(this)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingPopUp = PopupBoardMoreMenuBinding.inflate(layoutInflater)

    }

    override fun onResume() {
        super.onResume()

        val popupWindow = PopupWindow(bindingPopUp.root, WRAP_CONTENT, WRAP_CONTENT).apply {
            isFocusable = true
            isOutsideTouchable = true
            overlapAnchor = true
        }

        binding.anchorView.setOnClickListener {
            popupWindow.showAsDropDown(binding.anchorView, 0, 0)
            popupWindow.contentView.setBackgroundColor(Color.GRAY)

            val childViewFromPopupWindow = popupWindow.contentView.findViewById<TextView>(R.id.second_text)
            childViewFromPopupWindow.post {
                balloon.showAlignBottom(popupWindow.contentView)
            }
        }
    }
}