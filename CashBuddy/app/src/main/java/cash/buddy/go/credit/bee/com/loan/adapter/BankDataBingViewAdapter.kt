package cash.buddy.go.credit.bee.com.loan.adapter


import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import cash.buddy.go.credit.bee.com.loan.R
import com.google.android.material.textfield.TextInputLayout
import android.content.res.ColorStateList
import android.graphics.Color
import android.R.string





object BankDataBingViewAdapter {



    @BindingAdapter(value = ["bankNumber"], requireAll = true)
    @JvmStatic
    fun bankNumber(view: TextView, bankNumber: String) {
        if (bankNumber.length>=4){//壳app没有判断卡号长度,先简单这样写
            val result: String = bankNumber.substring(bankNumber.length - 4, bankNumber.length)
            view.text = "**** **** **** $result"
        }else{
            view.text = "**** **** **** $bankNumber"
        }

    }


}