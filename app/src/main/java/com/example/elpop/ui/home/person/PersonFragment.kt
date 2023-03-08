package com.example.elpop.ui.home.person

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
import com.example.elpop.adapters.PersonAdapter
import com.example.elpop.data.Clothe
import com.example.elpop.data.Person
import com.example.elpop.databinding.FragmentPersonBinding
import com.example.elpop.databinding.PersoncardBinding
import com.example.elpop.ui.home.HomeFragmentDirections
import com.example.elpop.ui.home.userhome.HomeViewModel
import java.util.ArrayList

class PersonFragment : Fragment() {
    private val viewModel: PersonViewModel by lazy {
        ViewModelProvider(this).get(PersonViewModel::class.java)
    }
    lateinit var binding: FragmentPersonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPersonBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel
        val adapter= PersonAdapter(
            PersonAdapter.PersonListenerClass{
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPersonInfoFragment(it))
            }
        )
        binding.allRC.adapter = adapter
        binding.addPerson.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_addPersonFragment)
        }
        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val tmpList: MutableList<Person> = ArrayList()

                viewModel.allPersons!!.value!!.forEach { person ->
                    if(person.name.contains(s.toString(), ignoreCase = true))
                        tmpList.add(person)
                }
                adapter.submitList(tmpList)
            }
        })
        return binding.root
    }
}