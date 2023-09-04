package com.test.mycontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.mycontacts.databinding.ItemRecyclerviewBinding


class ItemAdapter(val mItems: MutableList<MyItems>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null



    //2. 그 홀더를 onCreateViewHolder에서 리턴하게 된다.(item_Recyclerview LayOut을 가져온다.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }



//3. activity_main.xml  리스트에서 한 개의 항목을 불러올때 마다 MyAdapter.kt에서 onBindviewHolder가 생성이 된다.
//    그러면 그 holder랑 position을 받아서 그 해당 홀더에 아이콘 사진을 넣고 name과 age를 집어넣게 된다.
//    전에 listView에서는 getView를 했었는데 그거랑 같은 기능이라고 생각하면 쉽다.


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.name.text = mItems[position].aName
        holder.like.setImageResource(mItems[position.alike])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }





    //1.일단 홀더를 만들고
    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem
        val like = binding.lllike
    }
}