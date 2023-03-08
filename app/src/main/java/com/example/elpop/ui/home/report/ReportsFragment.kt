package com.example.elpop.ui.home.report

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
import com.example.elpop.adapters.ReportAdapter
import com.example.elpop.data.Clothe
import com.example.elpop.data.Report
import com.example.elpop.databinding.FragmentReportsBinding
import com.example.elpop.ui.home.HomeFragmentDirections
import com.example.elpop.ui.home.userhome.HomeViewModel
import java.util.ArrayList


class ReportsFragment : Fragment() {
    private val viewModel: ReportViewModel by lazy {
        ViewModelProvider(this).get(ReportViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentReportsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel
        val adapter= ReportAdapter()
        binding.allRC.adapter = adapter
        viewModel.getAllMoney()
        binding.welcomeTextId.text = (binding.welcomeTextId.text.toString()+" ("+viewModel.allReports.value!!.size.toString()+")"
                +" "+viewModel.allMoney.value.toString())
        binding.export.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
        binding.export.setOnClickListener(){
            binding.export.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
            binding.importId.setImageResource(R.drawable.ic_baseline_arrow_downward_24w)
            viewModel.getAll("ex")
            viewModel.getAllMoney()
            binding.welcomeTextId.text = ("Reports"+" ("+viewModel.allReports.value!!.size.toString()+")"
                    +" "+viewModel.allMoney.value.toString())
        }
        binding.importId.setOnClickListener(){
            binding.export.setImageResource(R.drawable.ic_baseline_arrow_upward_24w)
            binding.importId.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            viewModel.getAll("im")
            viewModel.getAllMoney()
            binding.welcomeTextId.text = ("Reports"+" ("+viewModel.allReports.value!!.size.toString()+")"
                    +" "+viewModel.allMoney.value.toString())
        }
        binding.addId.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_addReportFragment)
        }

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val tmpList: MutableList<Report> = ArrayList()

                viewModel.allReports!!.value!!.forEach { report ->
                    if(report.name.contains(s.toString(), ignoreCase = true)||report.date.contains(s.toString(), ignoreCase = true))
                        tmpList.add(report)
                }
                adapter.submitList(tmpList)
            }
        })
        return binding.root
    }
}