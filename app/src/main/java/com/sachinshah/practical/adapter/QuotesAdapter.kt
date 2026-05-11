package com.sachinshah.practical.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sachinshah.practical.R
import com.sachinshah.practical.databinding.RowListItemBinding
import com.sachinshah.practical.model.ResultModel

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

