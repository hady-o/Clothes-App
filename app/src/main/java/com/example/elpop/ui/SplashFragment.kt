package com.example.elpop.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.elpop.R
import com.example.elpop.databinding.FragmentSplashBinding
import com.shashank.sony.fancytoastlib.FancyToast

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentSplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
                     login()
        }, 3000)
        return binding.root
    }
fun  login(){
    var alert = AlertDialog.Builder(requireContext())
    alert.setTitle("enter password")
    alert.setMessage("please enter your password")
    val input = EditText(requireContext())
    alert.setView(input)
    alert.setPositiveButton("Ok", DialogInterface.OnClickListener(){ dilog, id ->
        if(input.text.isEmpty()){
            FancyToast.makeText(requireContext(),"please enter password",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()

        }else{
            if(input.text.toString()=="abdmo29"){
                this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment(9))
                FancyToast.makeText(requireContext(),"success login",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
            }else{
                FancyToast.makeText(requireContext(),"wrong login",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                login()
            }

        }
    }).create().show()
}
}