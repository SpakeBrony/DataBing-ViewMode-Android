package cash.buddy.go.credit.bee.com.loan.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import android.app.DatePickerDialog
import android.os.Handler
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityBankBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardTwoInputBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityUserInputBinding
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.viewmodel.BankModel
import java.util.*


class BankActivity : BaseActivity() {


    private val appModel by lazy {
        BankModel(this)
    }

    private lateinit var binding: ActivityBankBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")
        if (intent.getBooleanExtra("title", false))
            binding.indicator.visibility = View.VISIBLE
        else
            binding.indicator.visibility = View.GONE

        // or manual change
        binding.indicator.currentStep = 4
        binding.viewModel = appModel

        binding.userName.setText(StorePreferences.getInstance(this).user_name)


        binding.next.setOnClickListener {

            appModel.addBankBean(this, appModel.bankBean.value!!)
            StorePreferences.getInstance(this).user_number = 5
            loadingProgressBar.visibility = View.VISIBLE
            binding.next.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                finish()
                if (intent.getBooleanExtra("title", false))
                    Toast.makeText(this, "Auditing, please wait for the result", Toast.LENGTH_LONG)
                        .show()
            }, 2000)
        }

    }


}