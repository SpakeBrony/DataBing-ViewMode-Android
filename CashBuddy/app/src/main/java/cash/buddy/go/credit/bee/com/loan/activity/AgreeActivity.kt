package cash.buddy.go.credit.bee.com.loan.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityAgreeBinding
import cash.buddy.go.credit.bee.com.loan.utils.Utils

class AgreeActivity : BaseActivity() {


    private lateinit var binding: ActivityAgreeBinding

    private val LOCATION_CODE = 2346

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")

        binding.next.setOnClickListener {
            if (Utils.checkPermissionFirst(
                    this, LOCATION_CODE,
                    arrayOf(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_SMS
                    )
                )
            ) {
                finish()
                startActivity(Intent(this, IDCardRuleActivity::class.java))

            }
        }
        initWebView("https://cash-buddy.net/disclosure.html",binding.webView)
//        loadingProgressBar.visibility = View.VISIBLE
    }



    private fun initWebView(str: String,webview: WebView) {
        val webSettings: WebSettings = webview.settings
        webSettings.javaScriptEnabled = true
//        //等比例缩放
//        webSettings.useWideViewPort = true
//        webSettings.loadWithOverviewMode = true
//        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
//        webSettings.setSupportZoom(true)
//        //SMALLEST(50%),SMALLER(75%),NORMAL(100%),LARGER(150%),LARGEST(200%);
//        webSettings.textSize = WebSettings.TextSize.SMALLER
//        webview.settings.textZoom = 100
//        webview.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
//                super.onPageFinished(view, url)
//            }
//        }
        webview.loadUrl(str)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var isPermissions = true
        when (requestCode) {
            LOCATION_CODE -> {
                for (i in permissions.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        isPermissions = false
                    }
                }

                if (isPermissions) {
                    finish()
                    startActivity(Intent(this, IDCardRuleActivity::class.java))
                }
            }
        }
    }
}