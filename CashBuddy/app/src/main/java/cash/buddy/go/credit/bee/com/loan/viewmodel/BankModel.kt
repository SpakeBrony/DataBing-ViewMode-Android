package cash.buddy.go.credit.bee.com.loan.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cash.buddy.go.credit.bee.com.loan.bean.BankBean
import cash.buddy.go.credit.bee.com.loan.database.AppDatabase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class BankModel(val mActivity: Activity) : ViewModel() {


    //登录按钮是否可点击
    val bankBean = MutableLiveData(BankBean("", "", "", ""))
    val bankBeanList = MutableLiveData<ArrayList<BankBean>>(ArrayList())

    fun addBankBean(context: Context, bankBean: BankBean) {
        Thread {
            AppDatabase.get(context).bankDao().insert(bankBean)
        }.start()
    }

    fun listBankBean(context: Context) {
        Thread {
            val all = AppDatabase.get(context).bankDao().getAll()
            viewModelScope.launch {
                val arrayList :ArrayList<BankBean> = ArrayList()
                all.forEach {
                    arrayList.add(it)
                }
                bankBeanList.value = arrayList
            }
        }.start()

    }

}