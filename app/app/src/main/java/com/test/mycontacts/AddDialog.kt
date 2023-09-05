package com.test.mycontacts


import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
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

            if(condition(name,number, mail)) {

                onClickedListener.onClicked(name,number,mail)
                dismiss()
            }else
            {
                Toast.makeText(context,"빈칸을 채워주세요.",Toast.LENGTH_LONG).show()
            }

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

    private fun condition(name : String,number: String,mail : String):Boolean{ // condition 함수를 통해 빈칸이 있을 경우 false 반환
        if(name.isEmpty() || number.isEmpty() || mail.isEmpty())
        {
            return false
        }

        return true
    }
}

