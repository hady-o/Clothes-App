package com.example.elpop.ui.home.userhome

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
import com.example.elpop.databinding.FragmentInfoBinding
import com.example.elpop.ui.home.cart.CartViewModel
import com.shashank.sony.fancytoastlib.FancyToast


class InfoFragment : Fragment() {
    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    var edited = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentInfoBinding.inflate(layoutInflater)
        val args = arguments?.let { com.example.elpop.ui.home.userhome.InfoFragmentArgs.fromBundle(it) }
        args?.let {
            var c =it.clothe
            if(c.type=="cloth")
            {
                binding.image.setImageResource(R.drawable.icy_clothes_svgrepo_com)
            }else if(c.type=="foot")
            {
                binding.image.setImageResource(R.drawable.icy_footwear_shoes_svgrepo_com)
            }else
            {
                binding.image.setImageResource(R.drawable.icy_accessories_apparel_cap_svgrepo_com)
            }
            var cart = viewModel.getCartById(c.id)
            if(cart!=null)
            {
                binding.want.text=cart.itemQuantity.toString()
            }
            binding.name.setText(c.clothName)
            binding.available.setText(c.number.toString())
            binding.price.setText(c.price.toString())
            binding.total.setText((binding.want.text.toString().toInt()*c.price).toString())
            binding.backBtn.setOnClickListener(){
                this.findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToHomeFragment2(0))
            }
            binding.add.setOnClickListener(){
                if(c.number<=0)
                {
                    Toast.makeText(requireContext(),"Invalid number",Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()+1).toString()
                    c.number=c.number-1
                    binding.available.setText(c.number.toString())
                    binding.total.setText((binding.want.text.toString().toInt()*c.price).toString())
                }
            }
            binding.remove.setOnClickListener(){
                if(binding.want.text.toString().toInt()<=0)
                {
                    Toast.makeText(requireContext(),"Invalid number",Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()-1).toString()
                    c.number=c.number+1
                    binding.available.setText(c.number.toString())
                    binding.total.setText((binding.want.text.toString().toInt()*c.price).toString())
                }
            }
            binding.deleteBtn.setOnClickListener(){
                homeViewModel.deleteClothe(c.id)
                this.findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToHomeFragment2(0))
                FancyToast.makeText(requireContext(),"item has been deleted successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
            }
            binding.editBtn.setOnClickListener(){
                binding.name.isEnabled=true
                binding.available.isEnabled=true
                binding.price.isEnabled=true
                edited=1
            }
            binding.confirm.setOnClickListener(){
                if(edited==0) {
                    if (binding.want.text.toString().toInt() <= 0) {
                        FancyToast.makeText(
                            requireContext(),
                            "Invalid Process",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            true
                        ).show()
                    } else if (cart == null) {
                        viewModel.addToCart(
                            Cart(
                                c.id,
                                binding.want.text.toString().toInt(),
                                binding.want.text.toString().toInt() * c.price
                            )
                        )
                        this.findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToHomeFragment2(0))
                        FancyToast.makeText(
                            requireContext(),
                            "item has been added to cart successfully",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            true
                        ).show()

                    } else {
                        var t = binding.want.text.toString().toInt() * c.price
                        viewModel.updateCart(binding.want.text.toString().toInt(), t, c.id)
                        FancyToast.makeText(
                            requireContext(),
                            "item has been updated to cart successfully",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            true
                        ).show()
                        this.findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToHomeFragment2(0))
                    }
                    homeViewModel.updateClothe(c.number, c.id)
                }else
                {
                    homeViewModel.updateClothe(binding.available.text.toString().toInt(),
                    binding.name.text.toString(),
                    binding.price.text.toString().toDouble(),c.id)
                    FancyToast.makeText(
                        requireContext(),
                        "item has been updated successfully",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                    binding.name.isEnabled=false
                    binding.available.isEnabled=false
                    binding.price.isEnabled=false
                    edited=0
                }

            }
        }





        return binding.root
    }
}