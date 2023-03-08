package com.example.elpop.ui.home.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.data.Person
import com.example.elpop.data.Report
import com.example.elpop.databinding.FragmentAddReportBinding
import com.example.elpop.ui.home.history.HistoryInfoFragmentDirections
import com.example.elpop.ui.home.person.PersonViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*

class AddReportFragment : Fragment() {
    private val viewModel: ReportViewModel by lazy {
        ViewModelProvider(this).get(ReportViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddReportBinding.inflate(layoutInflater)
        binding.backBtn.setOnClickListener() {
            this.findNavController().navigate(AddReportFragmentDirections.actionAddReportFragmentToHomeFragment(4))

        }
        binding.doneBtn.setOnClickListener() {
            if (!binding.name.text.isEmpty() && !binding.price.text.isEmpty()) {
                val sdf = SimpleDateFormat("dd/M/yyyy",Locale.ENGLISH)
                val currentDate = sdf.format(Date())
                if(getReportType(binding)!=""){
                    viewModel.addReport(
                        Report(
                            binding.name.text.toString(),
                            getReportType(binding),
                            binding.price.text.toString().toDouble(),
                            currentDate
                        )
                    )
                    FancyToast.makeText(
                        requireContext(),
                        "Report has been added successfully",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    )
                        .show()
                    this.findNavController().navigate(AddReportFragmentDirections.actionAddReportFragmentToHomeFragment(4))

                }
            } else {
                FancyToast.makeText(
                    requireContext(),
                    getString(R.string.please_fill_all_data),
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                )
                    .show()
            }
        }

        return binding.root
    }

    fun getReportType(binding: FragmentAddReportBinding): String {
        var b = binding.radioGroup.checkedRadioButtonId
        if (b == binding.importId.id) {
            return "im"
        } else if (b == binding.exportId.id) {
            return "ex"
        } else {
            FancyToast.makeText(
                requireContext(),
                getString(com.example.elpop.R.string.please_select_type),
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR, true
            ).show()
            return ""
        }
    }
}