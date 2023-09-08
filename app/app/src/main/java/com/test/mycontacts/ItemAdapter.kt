package com.android.ex6_simplelistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.test.mycontacts.MyItems
import com.test.mycontacts.databinding.ItemRecyclerviewBinding
import com.test.mycontacts.databinding.ItemRecyclerview2Binding
import com.test.mycontacts.MyItems.Companion.defaultDataList

class MyAdapter(private val mItems: MutableList<MyItems>) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SM = 1
        private const val VIEW_TYPE_JYP = 2

    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    //3.ViewGroup으로 recycleview가 들어올거고, 그다음 int값으로 VIEW_TYPE_SM = 1이나 VIEW_TYPE_JYP = 2가 들어온다

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            VIEW_TYPE_SM -> {
                val binding =
                    ItemRecyclerviewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                SmViewHolder(binding)
            }

            VIEW_TYPE_JYP -> {
                //뷰타입 하나 일땐 이거만 있었다.
                val binding =
                    ItemRecyclerview2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                JypViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    //실제로 화면이 실행됐을때 한 줄씩 불러준다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        when (holder.itemViewType) {
            VIEW_TYPE_SM -> (holder as SmViewHolder).bind(item)
            VIEW_TYPE_JYP -> (holder as JypViewHolder).bind(item)
        }

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
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
        return if (position % 2 == 0) VIEW_TYPE_SM else VIEW_TYPE_JYP
    }

    //1. inner class에 holder를 만드는데 2가지 레이아웃으로 뿌려줘야 하니까 2가지 Holder를 만든다.
    inner class JypViewHolder(binding: ItemRecyclerview2Binding) :
        ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem
        val likeImageView = binding.like

        fun bind(item: MyItems) {
            if (item is MyItems.Item) {
                iconImageView.setImageResource(item.aIcon)
                name.text = item.aName
                likeImageView.setImageResource(item.alike1)
            }
        }

    }


    inner class SmViewHolder(binding: ItemRecyclerviewBinding) :
        ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem
        val likeImageView = binding.like

        fun bind(item: MyItems) {
            if (item is MyItems.Item) {
                iconImageView.setImageResource(item.aIcon)
                name.text = item.aName
                likeImageView.setImageResource(item.alike1)
            }
        }

    }
}
