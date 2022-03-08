package cash.buddy.go.credit.bee.com.loan.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import android.app.DatePickerDialog
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.ContactsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.DatePicker
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cash.buddy.go.credit.bee.com.loan.adapter.BankAdapter
import cash.buddy.go.credit.bee.com.loan.databinding.*
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.utils.Utils
import cash.buddy.go.credit.bee.com.loan.viewmodel.BankModel
import java.io.File
import java.io.FileNotFoundException
import java.util.*


class UserBankActivity : BaseActivity() {


    private lateinit var binding: ActivityUserBankBinding

    private val mAdapter : BankAdapter by lazy {
        BankAdapter(this)
    }

    private val appModel by lazy {
        BankModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("BANK A/C")
        binding.listView.run {
            val linearLayoutManager = GridLayoutManager(this@UserBankActivity,1)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = mAdapter
        }


        appModel.bankBeanList.observe(this){
            mAdapter.remove()
            mAdapter.addData(it)
        }

        binding.addBank.setOnClickListener {
            val intent = Intent(this, BankActivity::class.java)
            intent.putExtra("title",false)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

    }


    override fun onResume() {
        super.onResume()
        appModel.listBankBean(this)
    }

}