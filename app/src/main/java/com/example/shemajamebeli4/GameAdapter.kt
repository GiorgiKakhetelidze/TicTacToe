package com.example.shemajamebeli4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli4.databinding.X0ItemBinding

typealias CardClick = (item: X0Item, position: Int, isX: Boolean) -> Unit

class GameAdapter : RecyclerView.Adapter<GameAdapter.ItemViewHolder>() {

    var cardList: MutableList<X0Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var isX = true
    lateinit var itemClick: CardClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            X0ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = cardList.size

    inner class ItemViewHolder(private val binding: X0ItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind() {
            binding.xyImgView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            setXO()
        }

        private fun setXO(){
            if (isX) {
                isX = false
                binding.xyImgView.setImageResource(R.mipmap.x)
                binding.xyImgView.isClickable = false
                binding.xyImgView.isFocusable = false
            } else {
                isX = true
                binding.xyImgView.setImageResource(R.mipmap.o)
                binding.xyImgView.isClickable = false
                binding.xyImgView.isFocusable = false
            }
        }

    }

}