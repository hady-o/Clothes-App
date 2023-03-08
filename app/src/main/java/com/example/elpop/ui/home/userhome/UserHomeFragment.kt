package com.example.elpop.ui.home.userhome

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.adapters.ClothAdapter
import com.example.elpop.data.Clothe
import com.example.elpop.data.History
import com.example.elpop.databinding.FragmentHome2Binding
import com.example.elpop.ui.home.HomeFragmentDirections
import java.util.ArrayList


class UserHomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHome2Binding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel
        val adapter= ClothAdapter(
                ClothAdapter.ClothListenerClass{
                    this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInfoFragment2(it))
                }
        )
        binding.allRC.adapter = adapter
        binding.cloth.setImageResource(R.drawable.icy_clothes_svgrepo_com)
        binding.cloth.setOnClickListener(){
            binding.cloth.setImageResource(R.drawable.icy_clothes_svgrepo_com)
            binding.foot.setImageResource(R.drawable.ic_footwear_shoes_svgrepo_com)
            binding.acc.setImageResource(R.drawable.ic_accessories_apparel_cap_svgrepo_com)
            viewModel.getClothe("cloth")
        }
        binding.foot.setOnClickListener(){
            binding.cloth.setImageResource(R.drawable.ic_clothes_svgrepo_com)
            binding.foot.setImageResource(R.drawable.icy_footwear_shoes_svgrepo_com)
            binding.acc.setImageResource(R.drawable.ic_accessories_apparel_cap_svgrepo_com)
            viewModel.getClothe("foot")
        }
        binding.acc.setOnClickListener(){
            binding.cloth.setImageResource(R.drawable.ic_clothes_svgrepo_com)
            binding.foot.setImageResource(R.drawable.ic_footwear_shoes_svgrepo_com)
            binding.acc.setImageResource(R.drawable.icy_accessories_apparel_cap_svgrepo_com)
            viewModel.getClothe("acc")
        }
        binding.catId.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val tmpList: MutableList<Clothe> = ArrayList()

                viewModel.allClothes!!.value!!.forEach { clothe ->
                    if(clothe.clothName.contains(s.toString(), ignoreCase = true))
                        tmpList.add(clothe)
                }
                adapter.submitList(tmpList)
            }
        })

        return binding.root
    }

}
