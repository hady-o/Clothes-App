package com.example.elpop.ui.home.userhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.data.Clothe

import com.example.elpop.databinding.FragmentAddClotheBinding
import com.shashank.sony.fancytoastlib.FancyToast


class AddClotheFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddClotheBinding.inflate(layoutInflater)
        binding.doneBtn.setOnClickListener() {
            if (!binding.nameEditText.text.isEmpty() &&
                !binding.quantityEditText.text.isEmpty() &&
                !binding.pricrEditText.text.isEmpty()
            ) {
                var b=binding.radioGroup.checkedRadioButtonId
                if(b==binding.clothe.id){
                   addNewItem("cloth",binding)
                }else if(b==binding.acc.id){
                    addNewItem("acc",binding)
                }else if(b==binding.foot.id){
                    addNewItem("foot",binding)
                }
                else{
                    FancyToast.makeText(
                        requireContext(),
                        getString(com.example.elpop.R.string.please_select_type),
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,true
                    ).show()
                }
            }else{
                FancyToast.makeText(
                    requireContext(),
                    getString(com.example.elpop.R.string.please_fill_all_data),
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,true
                ).show()
            }

        }
        binding.backBtn.setOnClickListener(){
            this.findNavController().navigate(AddClotheFragmentDirections.actionAddClotheFragmentToHomeFragment(0))
        }
        return binding.root
    }

    fun addNewItem(s:String,binding:FragmentAddClotheBinding){
        viewModel.addClothe(Clothe(binding.nameEditText.text.toString(),
            s,
            binding.quantityEditText.text.toString().toInt(),
            binding.pricrEditText.text.toString().toDouble()))
        FancyToast.makeText(
            requireContext(),
            "item has been added successfully",
            FancyToast.LENGTH_LONG,
            FancyToast.SUCCESS,true
        ).show()
        this.findNavController().navigate(AddClotheFragmentDirections.actionAddClotheFragmentToHomeFragment(0))
    }

}



