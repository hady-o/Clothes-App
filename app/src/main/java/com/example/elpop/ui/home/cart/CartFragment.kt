package com.example.elpop.ui.home.cart

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.adapters.CartAdapter
import com.example.elpop.adapters.ClothAdapter
import com.example.elpop.data.History
import com.example.elpop.data.HistoryItem
import com.example.elpop.databinding.FragmentCartBinding
import com.example.elpop.ui.home.history.HistoryItemViewModel
import com.example.elpop.ui.home.history.HistoryViewModel

import com.example.elpop.ui.home.userhome.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*


class CartFragment : Fragment() {
    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val historyViewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }
    private val historyItemViewModel: HistoryItemViewModel by lazy {
        ViewModelProvider(this).get(HistoryItemViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCartBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vie=viewModel

        val adapter= CartAdapter(homeViewModel,viewModel,requireActivity())
        binding.allRC.adapter = adapter

        binding.backBtn.setOnClickListener(){
            this.findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment(0))
        }
        viewModel.getTotal()
        binding.total.text = viewModel.total.value!!.toString()
        binding.confirm.setOnClickListener(){
            var alert = AlertDialog.Builder(requireContext())
            alert.setTitle("edit Date")
            alert.setMessage("enter your date")
            val input = EditText(requireContext())
            input.inputType = InputType.TYPE_CLASS_DATETIME
            alert.setView(input)
            val sdf = SimpleDateFormat("dd/M/yyyy",Locale.ENGLISH)
            val currentDate = sdf.format(Date())
            alert.setPositiveButton("Ok", DialogInterface.OnClickListener(){dilog, id ->
                var h= input.text
                if(h.isEmpty()){
                    if(viewModel.total.value==0.0)
                    {
                        FancyToast.makeText(requireContext(),"invalid process",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()

                    }
                    else
                    {

                        var id = historyViewModel.addToHistory(History(
                            viewModel.total.value!!,
                            currentDate
                        ))
                        for (c in viewModel.allCart.value!!){
                            var item = homeViewModel.getClotheById(c.itemId)
                            historyItemViewModel.addToHistoryItems(HistoryItem(
                                id.toInt(),item.clothName,item.price,c.itemQuantity,item.id,item.type
                            ))
                        }
                        viewModel.deleteCart()
                        this.findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment(0))
                        FancyToast.makeText(requireContext(),"process has been confirmed successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                    }
                }
                else{
                    if(viewModel.total.value==0.0)
                    {
                        FancyToast.makeText(requireContext(),"invalid process",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()

                    }
                    else
                    {

                        var id = historyViewModel.addToHistory(History(
                            viewModel.total.value!!,
                            input.text.toString()
                        ))
                        for (c in viewModel.allCart.value!!){
                            var item = homeViewModel.getClotheById(c.itemId)
                            historyItemViewModel.addToHistoryItems(HistoryItem(
                                id.toInt(),item.clothName,item.price,c.itemQuantity,item.id,item.type
                            ))
                        }
                        viewModel.deleteCart()
                        this.findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment(0))
                        FancyToast.makeText(requireContext(),"process has been confirmed successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                    }
                }
            }).create().show()


        }
        binding.addToHistoryBtn.setOnClickListener(){
            var alert = AlertDialog.Builder(requireContext())
            alert.setTitle("edit receipt")
            alert.setMessage(getString(R.string.enter_receipt_id))
            val input = EditText(requireContext())
            input.inputType = InputType.TYPE_CLASS_NUMBER
            alert.setView(input)
            alert.setPositiveButton("Ok", DialogInterface.OnClickListener(){dilog, id ->
                if(!input.text.isEmpty())
                {
                    if(viewModel.total.value==0.0)
                    {
                        FancyToast.makeText(requireContext(),"invalid process",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()

                    }
                    else
                    {
                        historyViewModel.getHistoryById(input.text.toString().toInt())
                        var his = historyViewModel.history.value
                        if(his!=null)
                        {
                            for (c in viewModel.allCart.value!!){
                                var item = homeViewModel.getClotheById(c.itemId)
                                historyItemViewModel.addToHistoryItems(HistoryItem(
                                    input.text.toString().toInt(),item.clothName,item.price,c.itemQuantity,item.id,item.type
                                ))
                            }
                            viewModel.getTotal()
                            historyViewModel.updateHistory(his.total+viewModel.total.value!!,input.text.toString().toInt())
                            viewModel.deleteCart()
                            this.findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment(0))
                            FancyToast.makeText(requireContext(),"process has been confirmed successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                        }else{
                            FancyToast.makeText(requireContext(),"invalid history number",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()

                        }
                    }
                }
                else{
                    FancyToast.makeText(requireContext(),"please enter a valid number",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()

                }
            })
                .setNegativeButton("cancel", DialogInterface.OnClickListener(){dilog, id ->

                }).create()
            alert.show()
        }
        return binding.root
    }

}