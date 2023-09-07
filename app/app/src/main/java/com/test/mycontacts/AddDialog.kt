package com.test.mycontacts

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.test.mycontacts.databinding.DialogBinding

class AddDialog(context: Context, private val binding: DialogBinding) : Dialog(context) {
    var onDataAdded: (() -> Unit)? = null
    private lateinit var onClickedListener: ButtonClickListener
    private var notificationTime = 0

    fun dig() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        val saveButton = binding.save
        val cancelButton = binding.cancelbtn

        binding.off.setOnClickListener { notificationTime = 0 }
        binding.fivemin.setOnClickListener { notificationTime = 5 }
        binding.tenmin.setOnClickListener { notificationTime = 10 }
        binding.thirtymin.setOnClickListener { notificationTime = 30 }

        saveButton.setOnClickListener {
            val name = binding.name.text.toString()
            val number = binding.number.text.toString()
            val mail = binding.mail.text.toString()

            if(condition(name,number, mail)) {
                onClickedListener.onClicked(name, number, mail, notificationTime)
                onDataAdded?.invoke()
                dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    interface ButtonClickListener {
        fun onClicked(name: String, number: String, email: String, notificationTime:Int)
    }

    fun setOnButtonClickListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }

    private fun condition(name : String,number: String,mail : String):Boolean{
        if(name.isEmpty() || number.isEmpty() || mail.isEmpty()) {
            Toast.makeText(context,"빈칸을 채워주세요.",Toast.LENGTH_LONG).show()
            return false
        }

        if(!numberCondition(number)){
            Toast.makeText(context,"올바른 형식의 핸드폰 번호를 입력하세요. (하이픈)",Toast.LENGTH_LONG).show()
            return false
        }

        if(!mailCondition(mail)){
            Toast.makeText(context,"올바른 형식의 이메일을 입력하세요.",Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun mailCondition(mail : String):Boolean{
        val mail_condition = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}".toRegex()
        return mail_condition.matches(mail)
    }

    private fun numberCondition(number: String):Boolean{
        val number_condition = "^\\d{3}-\\d{3,4}-\\d{4}\$".toRegex()
        return number_condition.matches(number)
    }
}
