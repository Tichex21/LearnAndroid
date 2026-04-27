package com.demo.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.myapplication.R
import com.demo.myapplication.databinding.RowListItemBinding
import com.demo.myapplication.model.ResultModel

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuotesHolder>() {

    val arrayList = ArrayList<ResultModel>()


    fun setData(arrayList: ArrayList<ResultModel>){
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): QuotesHolder {

        val mBinding = DataBindingUtil.inflate<RowListItemBinding>(LayoutInflater.from(viewGroup.context), R.layout.row_list_item, viewGroup, false)

        return QuotesHolder(mBinding)


    }

    override fun onBindViewHolder(
        holder: QuotesHolder,
        position: Int
    ) {

        val model= arrayList[position]
        holder.mBinding.resultModel = model

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class QuotesHolder(val mBinding: RowListItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

    }
}

