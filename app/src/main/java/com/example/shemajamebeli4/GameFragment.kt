package com.example.shemajamebeli4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shemajamebeli4.databinding.FragmentGameBinding
import kotlin.math.sqrt


class GameFragment : Fragment() {

    private var binding: FragmentGameBinding? = null
    private val argument by navArgs<GameFragmentArgs>()
    private val adapter = GameAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val size: Int = argument.size
        val list = MutableList(sqrt(size.toDouble()).toInt() * sqrt(size.toDouble()).toInt()) { _ ->
            X0Item(ImageButton(requireContext()))
        }
        binding?.recycleView?.layoutManager =
            GridLayoutManager(requireContext(), sqrt(size.toDouble()).toInt())
        binding?.recycleView?.adapter = adapter
        adapter.cardList = list

        adapter.itemClick = { item,position, isX ->
            if (isX) {
                item.btn.setImageResource(R.mipmap.x)
                adapter.cardList[position] = item
                adapter.notifyItemChanged(position)
            }
            else{
                item.btn.setImageResource(R.mipmap.o)
                adapter.cardList[position] = item
                adapter.notifyItemChanged(position)
            }
        }
    }

}