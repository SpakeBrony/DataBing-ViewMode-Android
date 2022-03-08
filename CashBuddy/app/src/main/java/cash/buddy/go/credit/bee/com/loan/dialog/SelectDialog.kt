package cash.buddy.go.credit.bee.com.loan.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import cash.buddy.go.credit.bee.com.loan.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import cash.buddy.go.credit.bee.com.loan.databinding.SelectDialogBinding
import cash.buddy.go.credit.bee.com.loan.utils.DensityUtil

class SelectDialog(
    private val mContext: Context,
    private val data: Array<String>,
    private val function: (string:String) -> Unit
) :
    Dialog(
        mContext, R.style.SelectStyle
    ) {


    private lateinit var binding: SelectDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter =
            ArrayAdapter(mContext, R.layout.select_address_item, data) //新建并配置ArrayAapeter
        binding.listView.adapter = adapter
        setListViewHeight(binding.listView) //把上面的设置方法加到这里
        binding.listView.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            function(data[i])
            dismiss()
        }
        setViewLocation()
        setCanceledOnTouchOutside(true) //外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private fun setViewLocation() {
        val dm = DisplayMetrics()

        val mActivity :Activity = mContext as Activity

        mActivity.windowManager.defaultDisplay.getMetrics(dm)
        val height = dm.heightPixels
        val window = this.window
        val lp = window!!.attributes
        lp.x = 0
        lp.y = height
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // 设置显示位置
        onWindowAttributesChanged(lp)
    }

    private fun setListViewHeight(listView: ListView) {
        val listAdapter = listView.adapter ?: return //得到ListView 添加的适配器
        val itemView = listAdapter.getView(0, null, listView) //获取其中的一项
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0, 0)
        val itemHeight = itemView.measuredHeight //一项的高度
        val itemCount = listAdapter.count //得到总的项数
        var layoutParams: LinearLayout.LayoutParams? = null //进行布局参数的设置
        layoutParams = if (itemCount > 9) {
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(mContext, 400f)
            )
        } else {
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        listView.layoutParams = layoutParams
    }

}