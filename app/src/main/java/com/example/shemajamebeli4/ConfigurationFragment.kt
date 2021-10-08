package com.example.shemajamebeli4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.shemajamebeli4.databinding.FragmentConfigurationBinding


class ConfigurationFragment : Fragment() {

    private var binding: FragmentConfigurationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.startBtn?.setOnClickListener {
            val input = binding?.xSizeEditTxtView?.text.toString().toInt()
            if (input == 9 || input == 16 || input == 25){
                val action = ConfigurationFragmentDirections.actionConfigurationFragmentToGameFragment(
                    binding?.xSizeEditTxtView?.text.toString().toInt()
                )
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(requireContext(), "Input is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

}