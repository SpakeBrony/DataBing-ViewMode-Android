package cash.buddy.go.credit.bee.com.loan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

import android.app.Activity

import androidx.databinding.DataBindingUtil
import cash.buddy.go.credit.bee.com.loan.R
import cash.buddy.go.credit.bee.com.loan.bean.BankBean
import cash.buddy.go.credit.bee.com.loan.databinding.BankItemBinding


class BankAdapter(val mActivity: Activity): RecyclerView.Adapter<BankAdapter.ViewHolder>() {



    private var list: ArrayList<BankBean> =  ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mActivity)
        val binding: BankItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.bank_item, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: BankItemBinding = DataBindingUtil.getBinding(holder.mView)!!
        binding.item = list[position]

    }

    override fun getItemCount(): Int {
       return this.list.size
    }

    fun remove(){
        this.list = ArrayList()
    }

    fun addData(it: ArrayList<BankBean>){
        remove()
        this.list.addAll(it)
        notifyDataSetChanged()
    }


    class ViewHolder(  //这个CardView采用两层操作
        val mView: View
    ) : RecyclerView.ViewHolder(mView) {
    }
}