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
import cash.buddy.go.credit.bee.com.loan.databinding.*
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.utils.Utils
import java.io.File
import java.io.FileNotFoundException
import java.util.*


class EndActivity : BaseActivity() {


    private lateinit var binding: ActivityEndBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("APPLY PROGRESS")


    }


}