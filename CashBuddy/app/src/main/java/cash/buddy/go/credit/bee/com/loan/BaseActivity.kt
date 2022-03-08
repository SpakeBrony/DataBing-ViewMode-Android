package cash.buddy.go.credit.bee.com.loan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import cash.buddy.go.credit.bee.com.loan.utils.DensityUtil
import me.zhanghai.android.materialprogressbar.MaterialProgressBar


open class BaseActivity : AppCompatActivity() {



    private var _materialProgressBar : MaterialProgressBar?=null
     val loadingProgressBar get() = _materialProgressBar!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    /**
     * 顶部添加loading条
     * title:名称
     */
    fun initLoadIng(title:String) {
        val view: View = layoutInflater.inflate(R.layout.horizontal_title, null)
        val titleView:TextView = view.findViewById(R.id.title_view)
        _materialProgressBar = view.findViewById(R.id.materialProgressBar)
        titleView.text = title
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            DensityUtil.dip2px(this,55f)
        )
        addContentView(view, params)
        if (title.isEmpty()){//如果没有title隐藏顶部
            view.visibility = View.GONE
        }
    }
}

