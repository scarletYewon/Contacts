package com.test.mycontacts


import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.test.mycontacts.databinding.DialogBinding

class AddDialog(context: Context, private val binding: DialogBinding) : Dialog(context) {

    private lateinit var onClickedListener: ButtonClickListener

    fun dig() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false) // 다이얼로그 외의 부분을 눌렀을 때 꺼지지 않게 설정
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        val saveButton = binding.save
        val cancelButton = binding.cancelbtn

        saveButton.setOnClickListener {
            val name = binding.name.text.toString()
            val number = binding.number.text.toString()
            val mail = binding.mail.text.toString()

            onClickedListener.onClicked(name, number, mail)
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    interface ButtonClickListener {
        fun onClicked(name: String, number: String, email: String)
    }

    fun setOnButtonClickListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }
}

