package com.example.elpop.ui.home.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.data.Cart
import com.example.elpop.data.Clothe
import com.example.elpop.databinding.FragmentHistoryItemEditBinding
import com.example.elpop.loadImage
import com.example.elpop.ui.home.HomeFragmentDirections
import com.example.elpop.ui.home.userhome.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast

class HistoryItemEditFragment : Fragment() {
    private val viewModel: HistoryItemViewModel by lazy {
        ViewModelProvider(this).get(HistoryItemViewModel::class.java)
    }
    private val historyViewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }
    private val  homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHistoryItemEditBinding.inflate(layoutInflater)
        val args = arguments?.let { HistoryItemEditFragmentArgs.fromBundle(it) }
        args?.let {
            var item = it.item
            binding.his =item
            var oldTotal=item.quantity*item.price
            historyViewModel.getHistoryById(item.id)
            binding.total.setText((item.quantity*item.price).toString())
            loadImage(binding.image,item.clotheType)
            var clothe =homeViewModel.getClotheById(item.clotheId)
            if(clothe!=null){
                binding.available.setText(clothe.number.toString())
            }else{
                binding.available.setText("0")
            }
            binding.add.setOnClickListener(){
                if(binding.available.text.toString().toInt()<=0||clothe==null)
                {
                    Toast.makeText(requireContext(),"Invalid number", Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()+1).toString()
                    binding.available.setText((binding.available.text.toString().toInt()-1).toString())
                    binding.total.setText((binding.want.text.toString().toInt()*item.price).toString())
                }
            }
            binding.remove.setOnClickListener(){
                if(binding.want.text.toString().toInt()<=1)
                {
                    Toast.makeText(requireContext(),"Invalid number", Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()-1).toString()
                    binding.available.setText((binding.available.text.toString().toInt()+1).toString())
                    binding.total.setText((binding.want.text.toString().toInt()*item.price).toString())
                }
            }
//            binding.deleteBtn.setOnClickListener(){
//                viewModel
//                Navigation.findNavController(binding.root).navigate(R.id.action_infoFragment_to_homeFragment2)
//                FancyToast.makeText(requireContext(),"item has been deleted successfully",
//                    FancyToast.LENGTH_LONG,
//                    FancyToast.SUCCESS,true).show()
//            }

            binding.confirm.setOnClickListener() {
                if(clothe!=null){
                    homeViewModel.updateClothe(binding.available.text.toString().toInt(),clothe.id)
                    viewModel.updateItem(item.hId,binding.want.text.toString().toInt())
                    historyViewModel.getHistoryById(item.id)
                    historyViewModel.updateHistory(historyViewModel.history.value!!.total-oldTotal
                            +binding.total.text.toString().toDouble(),item.id)
                     this.findNavController().navigate(HistoryItemEditFragmentDirections.actionHistoryItemEditFragmentToHistoryInfoFragment(item.id))
                }else{
                    viewModel.updateItem(item.hId,binding.want.text.toString().toInt())
                    historyViewModel.getHistoryById(item.id)
                    historyViewModel.updateHistory(historyViewModel.history.value!!.total-oldTotal
                            +binding.total.text.toString().toDouble(),item.id)
                        if(binding.available.text.toString().toInt()>0)
                        {
                            homeViewModel.addClothe(Clothe(
                                item.name,item.clotheType,binding.available.text.toString().toInt(),item.price
                            ))
                        }
                    this.findNavController().navigate(HistoryItemEditFragmentDirections.actionHistoryItemEditFragmentToHistoryInfoFragment(item.id))
                }
            }
            binding.deleteBtn.setOnClickListener(){
                if(clothe!=null){
                    homeViewModel.updateClothe(clothe.number+item.quantity,clothe.id)
                    historyViewModel.getHistoryById(item.id)
                    historyViewModel.updateHistory(historyViewModel.history.value!!.total-oldTotal,item.id)
                    viewModel.deleteItem(item.hId)
                    this.findNavController().navigate(HistoryItemEditFragmentDirections.actionHistoryItemEditFragmentToHistoryInfoFragment(item.id))
                }else{
                    historyViewModel.getHistoryById(item.id)
                    historyViewModel.updateHistory(historyViewModel.history.value!!.total-oldTotal,item.id)
                        homeViewModel.addClothe(Clothe(
                            item.name,item.clotheType,item.quantity,item.price
                        ))
                    viewModel.deleteItem(item.hId)
                    this.findNavController().navigate(HistoryItemEditFragmentDirections.actionHistoryItemEditFragmentToHistoryInfoFragment(item.id))
                }
            }
            binding.backBtn.setOnClickListener(){
                this.findNavController().navigate(HistoryItemEditFragmentDirections.actionHistoryItemEditFragmentToHistoryInfoFragment(item.id))
            }
        }

        return binding.root
    }
}