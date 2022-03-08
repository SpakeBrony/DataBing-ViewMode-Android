package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardOneBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardRuleBinding
import cash.buddy.go.credit.bee.com.loan.utils.Utils
import cash.buddy.go.credit.bee.com.loan.viewmodel.CardOneModel

import java.io.File
import java.io.FileNotFoundException

class IDCardOneActivity : BaseActivity() {

   private val TAKE_PHOTO = 18881 //声明一个请求码，用于识别返回的结果

    private val appModel by lazy {
        CardOneModel(this)
    }


    private lateinit var binding: ActivityIdCardOneBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")
        // or manual change
        binding.indicator.currentStep = 0


        appModel.imageUri.observe(this){
            binding.next.isEnabled = it != null
        }

        binding.next.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            binding.next.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                finish()
                startActivity(Intent(this, IDCardOneInputActivity::class.java))
                overridePendingTransition(0, 0)
            }, 2000)
        }

        binding.addImage.setOnClickListener {
            initImage()
        }


    }

   private fun initImage(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
       appModel.imageUri.value = Utils.getDestinationUri(this)
       appModel.imageUri.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(this, "$packageName.fileProvider", File(appModel.imageUri.value!!.path!!))
        } else {
           Utils.getDestinationUri(this)
        }
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, appModel.imageUri.value)
        startActivityForResult(intent, TAKE_PHOTO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO ->{
                try {
                    val bitmap = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            appModel.imageUri.value!!
                        )
                    )
                    binding.cardImage.setImageBitmap(bitmap)
                    binding.addCardImage.visibility = View.GONE
                } catch (e: FileNotFoundException) {
                    appModel.imageUri.value = null
                    e.printStackTrace()
                }
            }
        }
    }
}