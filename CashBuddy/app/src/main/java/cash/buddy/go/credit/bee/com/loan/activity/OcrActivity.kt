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
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardTwoInputBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityOcrBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityUserInputBinding
import cash.buddy.go.credit.bee.com.loan.dialog.SelectDialog
import cash.buddy.go.credit.bee.com.loan.utils.SelectOnClick
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.utils.Utils
import java.io.File
import java.io.FileNotFoundException
import java.util.*


class OcrActivity : BaseActivity() {


    private lateinit var binding: ActivityOcrBinding

    private val TAKE_PHOTO = 18281 //声明一个请求码，用于识别返回的结果

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOcrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")

        // or manual change
        binding.indicator.currentStep = 3


        binding.next.setOnClickListener {

            initImage()
        }


    }

    private fun initImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        imageUri = Utils.getDestinationUri(this)
        imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(this, "$packageName.fileProvider", File(imageUri!!.path!!))
        } else {
            Utils.getDestinationUri(this)
        }
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, TAKE_PHOTO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO -> {
                try {
                    val bitmap = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            imageUri!!
                        )
                    )
                    StorePreferences.getInstance(this).user_number = 4
                    loadingProgressBar.visibility = View.VISIBLE
                    binding.next.isEnabled = false
                    val handler = Handler()
                    handler.postDelayed({
                        finish()
                        val intent = Intent(this, BankActivity::class.java)
                        intent.putExtra("title",true)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }, 2000)
                } catch (e: FileNotFoundException) {
                    imageUri = null
                    e.printStackTrace()
                }
            }
        }
    }

}