package com.example.elpop.ui.home.person

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
import com.example.elpop.databinding.FragmentPersonInfoBinding
import com.example.elpop.ui.home.history.HistoryInfoFragmentDirections
import com.example.elpop.ui.home.report.ReportViewModel

import com.example.elpop.ui.home.userhome.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*

class PersonInfoFragment : Fragment() {
    private val viewModel: PersonViewModel by lazy {
        ViewModelProvider(this).get(PersonViewModel::class.java)
    }
    private val reportViewModel: ReportViewModel by lazy {
        ViewModelProvider(this).get(ReportViewModel::class.java)
    }
    var edited = 0
    lateinit var person:Person
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPersonInfoBinding.inflate(layoutInflater)
        val args = arguments?.let { PersonInfoFragmentArgs.fromBundle(it) }
        args?.let {
            viewModel.getPersonById(it.clothe.id)
            person=it.clothe
        }
        binding.name.setText(viewModel.person.value!!.name)
        binding.salary.setText(viewModel.person.value!!.salary.toString())
        binding.rest.setText(viewModel.person.value!!.rest.toString())
        binding.phone.setText(viewModel.person.value!!.phone)
        binding.attend.setOnClickListener(){
            viewModel.updatePersonRest(person.id,viewModel.person.value!!.rest+viewModel.person.value!!.salary)
            viewModel.getPersonById(person.id)
            binding.rest.setText(viewModel.person.value!!.rest.toString())

            FancyToast.makeText(requireContext(),"Success Process",
                FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
        }

        binding.take.setOnClickListener(){
            if(binding.takedMony.text.toString().isEmpty()){
                FancyToast.makeText(requireContext(),"please enter a valid number",
                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
            }else if(binding.takedMony.text.toString().toDouble()>viewModel.person.value!!.rest
                ||binding.takedMony.text.toString().toDouble()<0){
                FancyToast.makeText(requireContext(),"please enter a valid number",
                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
            }else{
                viewModel.updatePersonRest(person.id,
                    viewModel.person.value!!.rest-binding.takedMony.text.toString().toDouble())
                viewModel.getPersonById(person.id)
                binding.rest.setText(viewModel.person.value!!.rest.toString())
                val sdf = SimpleDateFormat("dd/M/yyyy",Locale.ENGLISH)
                val currentDate = sdf.format(Date())
                reportViewModel.addReport(
                    Report(
                        viewModel.person.value!!.name,
                        "ex",
                        binding.takedMony.text.toString().toDouble(),
                        currentDate
                    )
                )
                FancyToast.makeText(requireContext(),"Success Process",
                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
            }
        }
        binding.backBtn.setOnClickListener(){
            this.findNavController().navigate(PersonInfoFragmentDirections.actionPersonInfoFragmentToHomeFragment(2))
        }
        binding.deleteBtn.setOnClickListener(){
            viewModel.deletePerson(person.id)
            this.findNavController().navigate(PersonInfoFragmentDirections.actionPersonInfoFragmentToHomeFragment(2))
        }
        binding.editBtn.setOnClickListener(){
            if(edited == 0){
                binding.name.isEnabled =true
                binding.phone.isEnabled=true
                binding.salary.isEnabled=true
                edited=1
                binding.editBtn.setImageResource(R.drawable.ic_baseline_check_24)
            }else{
                if(binding.name.text.isEmpty()||
                        binding.phone.text.isEmpty()||
                        binding.salary.text.isEmpty()){
                    FancyToast.makeText(requireContext(),"please fill all data",
                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                }else{
                    viewModel.updatePerson(person.id,
                    binding.name.text.toString(),
                        binding.phone.text.toString(),
                        binding.salary.text.toString().toDouble()
                    )
                    FancyToast.makeText(requireContext(),"person has been updated successfully",
                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                }
                edited=0
                binding.name.isEnabled =false
                binding.phone.isEnabled=false
                binding.salary.isEnabled=false
                binding.editBtn.setImageResource(R.drawable.ic_baseline_edit_24)
            }

        }
        return binding.root
    }
}