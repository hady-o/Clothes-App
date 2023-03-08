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
import com.example.elpop.databinding.FragmentAddPersonBinding
import com.example.elpop.databinding.FragmentPersonBinding
import com.example.elpop.personBindRecyclerView
import com.example.elpop.ui.home.history.HistoryInfoFragmentDirections
import com.example.elpop.ui.home.userhome.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast


class AddPersonFragment : Fragment() {
    private val viewModel: PersonViewModel by lazy {
        ViewModelProvider(this).get(PersonViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddPersonBinding.inflate(layoutInflater)

        binding.backBtn.setOnClickListener() {
            this.findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToHomeFragment(3))

        }
        binding.addn.setOnClickListener(){
            if (!binding.name.text.isEmpty() && !binding.phone.text.isEmpty() && !binding.salary.text.isEmpty())
            {
                viewModel.addPerson(
                    Person(
                        binding.name.text.toString(),
                        binding.phone.text.toString(),
                        binding.salary.text.toString().toDouble(),
                        0.0
                    )
                )
                FancyToast.makeText(requireContext(),
                    "person has been added successfully",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    true)
                    .show()
                this.findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToHomeFragment(3))

            }else{
                FancyToast.makeText(requireContext(),
                    "please fill all data",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true)
                    .show()
            }
        }
        return binding.root
    }
}