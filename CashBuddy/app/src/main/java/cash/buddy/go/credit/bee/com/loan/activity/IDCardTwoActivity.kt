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
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardTwoBinding
import cash.buddy.go.credit.bee.com.loan.utils.Utils
import cash.buddy.go.credit.bee.com.loan.viewmodel.CardTwoModel

import java.io.File
import java.io.FileNotFoundException

class IDCardTwoActivity : BaseActivity() {

   private val TAKE_PHOTO1 = 18881 //声明一个请求码，用于识别返回的结果
   private val TAKE_PHOTO2 = 18882 //声明一个请求码，用于识别返回的结果

    private val appModel by lazy {
        CardTwoModel(this)
    }


    private lateinit var binding: ActivityIdCardTwoBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")
        // or manual change
        binding.indicator.currentStep = 1


        appModel.imageUri1.observe(this){
            binding.next.isEnabled = !(appModel.imageUri1.value==null||appModel.imageUri2.value==null)
        }
        appModel.imageUri2.observe(this){
            binding.next.isEnabled = !(appModel.imageUri1.value==null||appModel.imageUri2.value==null)
        }

        binding.next.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            binding.next.isEnabled = false
            val handler = Handler()
            handler.postDelayed({
                finish()
                startActivity(Intent(this, IDCardTwoInputActivity::class.java))
                overridePendingTransition(0, 0)
            }, 2000)
        }

        binding.addImage1.setOnClickListener {
            initImage(1)
        }

        binding.addImage2.setOnClickListener {
            initImage(2)
        }


    }

   private fun initImage(int: Int){
      if (int ==1){
          val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
          appModel.imageUri1.value = Utils.getDestinationUri(this)
          appModel.imageUri1.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
              FileProvider.getUriForFile(this, "$packageName.fileProvider", File(appModel.imageUri1.value!!.path!!))
          } else {
              Utils.getDestinationUri(this)
          }
          //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
          intent.putExtra(MediaStore.EXTRA_OUTPUT, appModel.imageUri1.value)
          startActivityForResult(intent, TAKE_PHOTO1)

      }else if (int == 2){
          val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
          appModel.imageUri2.value = Utils.getDestinationUri(this)
          appModel.imageUri2.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
              FileProvider.getUriForFile(this, "$packageName.fileProvider", File(appModel.imageUri2.value!!.path!!))
          } else {
              Utils.getDestinationUri(this)
          }
          //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
          intent.putExtra(MediaStore.EXTRA_OUTPUT, appModel.imageUri2.value)
          startActivityForResult(intent, TAKE_PHOTO2)
      }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO1 ->{
                try {
                    val bitmap = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            appModel.imageUri1.value!!
                        )
                    )
                    binding.cardImage1.setImageBitmap(bitmap)
                    binding.addCardImage1.visibility = View.GONE
                } catch (e: FileNotFoundException) {
                    appModel.imageUri1.value = null
                    e.printStackTrace()
                }
            }
            TAKE_PHOTO2 ->{
                try {
                    val bitmap = BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            appModel.imageUri2.value!!
                        )
                    )
                    binding.cardImage2.setImageBitmap(bitmap)
                    binding.addCardImage2.visibility = View.GONE
                } catch (e: FileNotFoundException) {
                    appModel.imageUri2.value = null
                    e.printStackTrace()
                }
            }
        }
    }
}