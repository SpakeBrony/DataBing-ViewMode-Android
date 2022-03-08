package cash.buddy.go.credit.bee.com.loan.viewmodel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mehdi.sakout.fancybuttons.FancyButton
import java.util.*

class LoginModel(val mActivity: Activity) : ViewModel() {

    private var timer: Timer? = null
    private val time = 60000
    private var timerTask: TimerTask? = null
    private var timess = 0




    //手机错误信息提示
    val phoneErrorMsg = "*Please enter the correct phone number"
    //验证码错误信息提示
    val codeErrorMsg = "*Please double-check the code you received and try again"
    //登录按钮是否可点击
    val loginButtonFlag =  MutableLiveData(false)


    fun getCode(view: View){
        val fancyButton  = view as FancyButton
        countDown(fancyButton)
    }


    private fun countDown(view: FancyButton) {
        view.isEnabled = false
        timess = time / 1000
        view.setText(timess.toString() + "S")
        if (timerTask == null) {
            timerTask = object : TimerTask() {
                override fun run() {
                    mActivity.runOnUiThread(Runnable {
                        timess--
                        view.setText(timess.toString() + "S")
                        if (timess <= 0) {
                            stopTimer(view)
                            return@Runnable
                        }
                    })
                }
            }
        }
        if (timer == null) {
            timer = Timer()
        }
        timer!!.schedule(timerTask, 1000, 1000)
    }

    private fun stopTimer(view: FancyButton) {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
        if (timerTask != null) {
            timerTask!!.cancel()
            timerTask = null
        }
        view.setText("Send")
        view.isEnabled = true
    }
}