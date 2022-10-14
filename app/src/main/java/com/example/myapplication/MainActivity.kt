package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.PopupBoardMoreMenuBinding
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingPopUpUnderBalloon: PopupBoardMoreMenuBinding

    private lateinit var bindingPopUpUnderPopUp: PopupBoardMoreMenuBinding
    private lateinit var bindingPopUpPopUpAbove: PopupBoardMoreMenuBinding

    private val balloon: Balloon by lazy {
        Balloon.Builder(this)
            .setIsVisibleOverlay(true) // comment this line and project will work but without overlay
            .setOverlayColorResource(R.color.purple_200)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setBalloonAnimation(BalloonAnimation.OVERSHOOT).setDismissWhenTouchOutside(false)
            .setDismissWhenShowAgain(false).setDismissWhenOverlayClicked(false)
            .setLifecycleOwner(this).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingPopUpUnderBalloon = PopupBoardMoreMenuBinding.inflate(layoutInflater)

        bindingPopUpUnderPopUp = PopupBoardMoreMenuBinding.inflate(layoutInflater)
        bindingPopUpPopUpAbove = PopupBoardMoreMenuBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()

        setPopUpFunctionality()
    }

    private fun setPopUpFunctionality(){
        val popupWindow = PopupWindow(bindingPopUpUnderPopUp.root, WRAP_CONTENT, WRAP_CONTENT).apply {
            isFocusable = true
            isOutsideTouchable = true
            overlapAnchor = true
        }

        val popupWindowAboveWindow = PopupWindow(bindingPopUpPopUpAbove.root, WRAP_CONTENT, WRAP_CONTENT).apply {
            isFocusable = true
            isOutsideTouchable = true
            overlapAnchor = true
        }

        binding.anchorForPopup.setOnClickListener {
            popupWindow.showAsDropDown(binding.anchorForPopup, 0, 0)
            popupWindow.contentView.setBackgroundColor(Color.GRAY)

            val childViewFromPopupWindow =
                popupWindow.contentView.findViewById<TextView>(R.id.second_text)

            popupWindowAboveWindow.showAsDropDown(childViewFromPopupWindow, 80, 80)
            popupWindowAboveWindow.contentView.setBackgroundColor(Color.GRAY)
        }
    }
}