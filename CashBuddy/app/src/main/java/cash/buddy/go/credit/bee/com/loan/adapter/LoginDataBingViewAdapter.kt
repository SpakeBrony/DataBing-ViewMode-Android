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


object LoginDataBingViewAdapter {



    @BindingAdapter(value = ["errorMsg", "numSize"], requireAll = true)
    @JvmStatic
    fun setError(view: TextInputLayout, errorMsg: String,numSize:Int) {
        view.isErrorEnabled = false
        val editText =  view.editText
        editText!!.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                view.isErrorEnabled = p0!!.length<numSize
                if (view.isErrorEnabled){
                    view.error = errorMsg
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }


}