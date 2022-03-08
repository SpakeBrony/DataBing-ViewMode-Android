package cash.buddy.go.credit.bee.com.loan.viewmodel

import android.app.Activity
import android.net.Uri
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mehdi.sakout.fancybuttons.FancyButton
import java.util.*

class CardTwoModel(val mActivity: Activity) : ViewModel() {

    //登录按钮是否可点击
    val imageUri1 =  MutableLiveData<Uri>(null)
    val imageUri2 =  MutableLiveData<Uri>(null)
}