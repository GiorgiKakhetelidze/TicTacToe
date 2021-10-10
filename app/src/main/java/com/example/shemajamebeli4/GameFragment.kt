package com.example.shemajamebeli4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
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
        val convertedSize = sqrt(size.toDouble()).toInt()

        val list = MutableList(convertedSize * convertedSize) { _ ->
            X0Item(ImageButton(requireContext()))
        }

        val resultList = MutableList(convertedSize) { MutableList(convertedSize) { "text" } }

        binding?.resetBtn?.setOnClickListener {
            adapter.resultList.clear()
            adapter.cardList.clear()
            adapter.notifyDataSetChanged()
            init()
        }

        binding?.recycleView?.layoutManager =
            GridLayoutManager(requireContext(), sqrt(size.toDouble()).toInt())
        binding?.recycleView?.adapter = adapter
        adapter.cardList = list
        adapter.resultList = resultList
        adapter.itemClick = { count ->
            if (count >= convertedSize + convertedSize - 1)
                checkWin(convertedSize)
        }
    }

    private fun checkWin(size: Int) {

        //Rows
        checkLeftToRight(size, adapter.resultList)

        //Columns
        val transposedList = transpose(adapter.resultList)
        checkLeftToRight(size, transposedList)

        //Diagonals
        checkMainDiagonal(size, adapter.resultList)

    }

    private fun checkMainDiagonal(size: Int, resultList: MutableList<MutableList<String>>) {
        val mainDiagonal = mutableListOf<String>()

        for (x in 0 until resultList.size) {
            for (y in 0 until resultList[x].size) {
                if (x == y) {
                    mainDiagonal.add(resultList[x][y])
                }
            }

        }

        if (size == mainDiagonal.size && mainDiagonal.toSet().size == 1 && mainDiagonal.first() == "x")
            Toast.makeText(requireContext(), "Winner is X", Toast.LENGTH_SHORT).show()
        else if (size == mainDiagonal.size && mainDiagonal.toSet().size == 1 && mainDiagonal.first() == "o")
            Toast.makeText(requireContext(), "Winner is O", Toast.LENGTH_SHORT).show()
    }

    private fun checkLeftToRight(size: Int, resultList: MutableList<MutableList<String>>) {
        for (list in resultList) {
            if (list.size == size && list.toSet().size == 1 && list.first() == "o")
                Toast.makeText(requireContext(), "Winner is O", Toast.LENGTH_SHORT).show()
            else if (list.size == size && list.toSet().size == 1 && list.first() == "x")
                Toast.makeText(requireContext(), "Winner is X", Toast.LENGTH_SHORT).show()
        }
    }

    private fun <T> transpose(list: MutableList<MutableList<T>>): MutableList<MutableList<T>> {
        val ret: MutableList<MutableList<T>> = mutableListOf()
        val size = list[0].size
        for (i in 0 until size) {
            val col: MutableList<T> = mutableListOf()
            for (row in list) {
                col.add(row[i])
            }
            ret.add(col)
        }
        return ret
    }
}