package com.sachinshah.practical.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sachinshah.practical.R
import com.sachinshah.practical.databinding.RowListItemBinding
import com.sachinshah.practical.model.ResultModel

class QuotesAdapter2 : ListAdapter<ResultModel, QuotesAdapter2.ResultItemHolder>(DiffUtil()) {

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ResultItemHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = DataBindingUtil.inflate<RowListItemBinding>(
            inflater, R.layout.row_list_item, p0, false
        )
        return ResultItemHolder(binding)
    }

    override fun onBindViewHolder(
        p0: ResultItemHolder,
        p1: Int
    ) {
        p0.binding.resultModel = getItem(p1)
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(
            p0: ResultModel, p1: ResultModel
        ): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(
            p0: ResultModel, p1: ResultModel
        ): Boolean {
            return p0 == p1
        }

    }

    class ResultItemHolder(val binding: RowListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}

