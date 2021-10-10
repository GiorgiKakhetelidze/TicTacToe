package com.example.shemajamebeli4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli4.databinding.X0ItemBinding
import kotlin.math.sqrt

typealias CardClick = (count: Int) -> Unit

class GameAdapter : RecyclerView.Adapter<GameAdapter.ItemViewHolder>() {

    var count = 0

    var cardList: MutableList<X0Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //val resultList = MutableList(cardList.size) {  MutableList(cardList.size) { "text" } }
    var resultList: MutableList<MutableList<String>> = mutableListOf()

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
            count++
            setXO()
            itemClick.invoke(count)
        }

        private fun setXO() {
            if (isX) {
                isX = false
                binding.xyImgView.setImageResource(R.mipmap.x)
                binding.xyImgView.isClickable = false
                binding.xyImgView.isFocusable = false
                val y =
                    if (adapterPosition == 0) 0 else adapterPosition / sqrt(cardList.size.toDouble()).toInt()
                val x = adapterPosition % sqrt(cardList.size.toDouble()).toInt()
                resultList[x][y] = "x"
            } else {
                isX = true
                binding.xyImgView.setImageResource(R.mipmap.o)
                binding.xyImgView.isClickable = false
                binding.xyImgView.isFocusable = false
                val y =
                    if (adapterPosition == 0) 0 else adapterPosition / sqrt(cardList.size.toDouble()).toInt()
                val x = adapterPosition % sqrt(cardList.size.toDouble()).toInt()
                resultList[x][y] = "o"
            }
        }
    }

}