package cash.buddy.go.credit.bee.com.loan.utils

import android.app.Activity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog

class SelectOnClick {

    val listUserInput1 = arrayOf("No deucation","primary school","secondary school","heigh scool","university","postgraduate","doctor")
    val listUserInput2 = arrayOf("unmarried","married","divorced","widowed")
    val listUserInput3 = arrayOf("Ordinary staff","Executive","Supervisor","Manager","Director","Other")
    val listUserInput4 = arrayOf("0-15000","15001-25000","25001-35000","35001-45000","45001-55000","55000 above")
    val listUserInput5 = arrayOf("Father","Relatives","Colleague","Friend")
    val listIDCardTowInput = arrayOf("FEMALE","MALE")

    fun onClick(view: View, data: Array<String>){
        val textView = view as EditText
        SelectDialog(textView.context,data){
            textView.setText(it)
        }.show()
    }
}