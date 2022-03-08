package cash.buddy.go.credit.bee.com.loan.adapter

import android.R.attr
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import cash.buddy.go.credit.bee.com.loan.adapter.CardAdapter
import androidx.cardview.widget.CardView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import cash.buddy.go.credit.bee.com.loan.R
import cash.buddy.go.credit.bee.com.loan.adapter.CardAdapter.MAX_ELEVATION_FACTOR
import java.util.ArrayList
import android.R.attr.mimeType

import android.webkit.WebViewClient

import android.webkit.WebSettings




class CardPagerAdapter : PagerAdapter(), CardAdapter {

    private val mData: ArrayList<String> = ArrayList()
    private var mBaseElevation = 0f
    private val mViews:ArrayList<CardView?> = ArrayList()

    fun addCardItem(item: String) {
        mData.add(item)
        mViews.add(null)
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews[position]
    }

    override fun getCount(): Int {
        return mViews.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context)
            .inflate(R.layout.card_adapter, container, false)
        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById<View>(R.id.cardView) as CardView

        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }
        cardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews[position] = null
    }

    private fun bind(item: String, view: View) {
        val webView = view.findViewById(R.id.web_view) as WebView
        initWebView(item,webView)
    }

    private fun initWebView(str: String,webview:WebView) {
        val webSettings: WebSettings = webview.settings
        webSettings.javaScriptEnabled = true
        //等比例缩放
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


}