package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import android.app.DatePickerDialog
import android.os.Handler
import android.widget.DatePicker
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardTwoInputBinding
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import java.util.*


class IDCardTwoInputActivity : BaseActivity(),DatePickerDialog.OnDateSetListener {



    private lateinit var binding: ActivityIdCardTwoInputBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardTwoInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setClick = SelectOnClick()
        initLoadIng("VERIFICATION")

        binding.dateInput.setOnClickListener {
            //获取日历的一个实例，里面包含了当前的年月日
            val calendar: Calendar = Calendar.getInstance()
            //构建一个日期对话框，该对话框已经集成了日期选择器
            //DatePickerDialog的第二个构造参数指定了日期监听器
            //构建一个日期对话框，该对话框已经集成了日期选择器
            //DatePickerDialog的第二个构造参数指定了日期监听器
            val dialog = DatePickerDialog(
                this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            //把日期对话框显示在界面上
            //把日期对话框显示在界面上
            dialog.show()
        }

        binding.next.setOnClickListener {
            StorePreferences.getInstance(this).user_number = 2
            loadingProgressBar.visibility = View.VISIBLE
            binding.next.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                finish()
                startActivity(Intent(this, UserInputActivity::class.java))
                overridePendingTransition(0, 0)
            }, 2000)
        }

    }

    //一旦点击日期对话框上的确定按钮，就会触发监听器的onDateSet方法
    override fun onDateSet(datePicker: DatePicker?, i: Int, i1: Int, i2: Int) {
        //获取日期对话框设定的年月份
        val desc = String.format("%d/%d/%d", i2, i1 + 1,i)
        binding.dateInput.setText(desc)
    }

}