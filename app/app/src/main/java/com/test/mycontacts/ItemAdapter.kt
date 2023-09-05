package com.android.ex6_simplelistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.test.mycontacts.MyItems
import com.test.mycontacts.databinding.FragmentJypBinding
import com.test.mycontacts.databinding.FragmentSmBinding

class MyAdapter(val mItems: MutableList<MyItems>) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SM = 1
        private const val VIEW_TYPE_JYP = 2

    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    //3.ViewGroup으로 recycleview가 들어올거고, 그다음 int값으로 VIEW_TYPE_SM = 1이나 VIEW_TYPE_JYP = 2가 들어온다
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_SM -> {
                val binding =
                    FragmentSmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SmViewHolder(binding)
            }

            else -> {
                //뷰타입 하나 일땐 이거만 있었다.
                val binding =
                    FragmentJypBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                JypViewHolder(binding)
            }
        }
    }

    //실제로 화면이 실행됐을때 한 줄씩 불러준다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = mItems[position]) {
            is MyItems.SmItem -> {
                (holder as SmViewHolder).iconImageView.setImageResource(item.aIcon)
                holder.name.text = "${item.aName} "
                holder.iconImageView.setImageResource(item.alike1)
            }
            is MyItems.jypItem -> {
                (holder as JypViewHolder).iconImageView.setImageResource(item.bIcon)
                holder.name.text = "${item.bName}"
                holder.iconImageView.setImageResource(item.blike2)

                holder.itemView.setOnClickListener {
                    itemClick?.onClick(it, position)
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    //2.position에 해당하는 타입이 뭔지 알려달라는 함수
    override fun getItemViewType(position: Int): Int {
        return when (mItems[position]) {
            is MyItems.SmItem -> VIEW_TYPE_SM
            is MyItems.jypItem -> VIEW_TYPE_JYP
        }
    }

    //1. inner class에 holder를 만드는데 2가지 레이아웃으로 뿌려줘야 하니까 2가지 Holder를 만든다.
    inner class JypViewHolder(binding: FragmentJypBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem
        val like = binding.like
    }

    inner class SmViewHolder(binding: FragmentSmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem
        val like = binding.like
    }
}