package com.example.elpop.ui.home.history

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.adapters.ClothAdapter
import com.example.elpop.adapters.HistoryAdapter
import com.example.elpop.data.History
import com.example.elpop.databinding.FragmentHistoryBinding
import com.example.elpop.ui.home.HomeFragmentDirections
import com.example.elpop.ui.home.userhome.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHistoryBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel
        val adapter= HistoryAdapter(
            HistoryAdapter.HistoryListenerClass{
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHistoryInfoFragment(it!!.historyId))
            }
        )
        viewModel.getTotalMoney()
        binding.welcomeTextId.text = (binding.welcomeTextId.text.toString()+" ("+viewModel.allHistoryData.value!!.size.toString()+")"
                +" "+viewModel.allMoney.value.toString())
        binding.today.setOnClickListener(){
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            viewModel.getHistoryByDate(currentDate)
            viewModel.getTotalMoney()
            binding.welcomeTextId.text = ("Your history"+" ("+viewModel.allHistoryData.value!!.size.toString()+")"
                    +" "+viewModel.allMoney.value.toString())
        }
        binding.allRC.adapter = adapter

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val tmpList: MutableList<History> = ArrayList()

                viewModel.allHistoryData!!.value!!.forEach { history ->
                    if(history.date.contains(s.toString(), ignoreCase = true))
                        tmpList.add(history)
                }
                adapter.submitList(tmpList)
            }
        })

        return binding.root
    }


}