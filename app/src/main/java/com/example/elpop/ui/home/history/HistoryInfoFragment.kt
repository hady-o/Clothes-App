package com.example.elpop.ui.home.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.adapters.HistoryAdapter
import com.example.elpop.adapters.HistoryItemAdapter
import com.example.elpop.databinding.FragmentHistoryInfoBinding
import com.example.elpop.ui.home.userhome.InfoFragmentArgs

class HistoryInfoFragment : Fragment() {
    private val viewModel: HistoryItemViewModel by lazy {
        ViewModelProvider(this).get(HistoryItemViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHistoryInfoBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel
        val args = arguments?.let { HistoryInfoFragmentArgs.fromBundle(it) }
        args?.let {
            viewModel.getAllItems(it.id)
        }
        binding.backBtn.setOnClickListener() {
            this.findNavController().navigate(HistoryInfoFragmentDirections.actionHistoryInfoFragmentToHomeFragment(1))
        }
        val adapter= HistoryItemAdapter()
        binding.allRC.adapter = adapter
        return binding.root
    }

}