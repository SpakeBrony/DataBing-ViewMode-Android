package cash.buddy.go.credit.bee.com.loan.viewmodel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cash.buddy.go.credit.bee.com.loan.bean.BankBean
import cash.buddy.go.credit.bee.com.loan.database.AppDatabase
import kotlinx.coroutines.launch
import mehdi.sakout.fancybuttons.FancyButton
import java.util.*

class MainModel(val mActivity: Activity) : ViewModel() {


    fun remove(function: () -> Unit) {
        Thread {
            AppDatabase.get(mActivity).bankDao().deleteAll()
            viewModelScope.launch {
                function()
            }
        }.start()
    }

    //登录按钮是否可点击
    val userPhone =  MutableLiveData("")
}