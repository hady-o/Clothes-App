package com.example.elpop.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.R
import com.example.elpop.data.Cart
import com.example.elpop.data.Clothe
import com.example.elpop.databinding.CartItemCardBinding
import com.example.elpop.databinding.ClothItemCardBinding
import com.example.elpop.loadImage
import com.example.elpop.ui.home.cart.CartViewModel
import com.example.elpop.ui.home.userhome.HomeViewModel
import com.shashank.sony.fancytoastlib.FancyToast

class CartAdapter(var homeViewModel: HomeViewModel, var viewModel: CartViewModel,var activity:Activity) :
    ListAdapter<Cart, CartAdapter.ViewHolder>(CartDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.fromViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cart = getItem(position)!!
        holder.clothBind(cart,homeViewModel, viewModel,activity)

    }


    class ViewHolder private constructor(val binding: CartItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun fromViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun clothBind(
            cart:Cart?,
            homeViewModel: HomeViewModel,
            viewModel: CartViewModel,
            activity:Activity
        ) {
            var cloth = homeViewModel.getClotheById(cart!!.itemId)
            loadImage(binding.clothImage,cloth.type)
            binding.clothName.text=cloth.clothName
            binding.clothPrice.text=cloth.price.toString()
            binding.want.text=cart.itemQuantity.toString()
            binding.delete.setOnClickListener(){
                homeViewModel.updateClothe(cloth.number+cart.itemQuantity,cloth.id)
                viewModel.deleteFromCart(cart.itemId)
                viewModel.getCart()
                viewModel.getTotal()
                activity.recreate()
            }
            binding.add.setOnClickListener(){
                if(cloth.number<=0)
                {
                    Toast.makeText(activity,"Invalid number", Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()+1).toString()
                    cloth.number=cloth.number-1
                    var t = binding.want.text.toString().toInt() * cloth.price
                    viewModel.updateCart(binding.want.text.toString().toInt(), t, cloth.id)
                    FancyToast.makeText(
                        activity,
                        "item has been updated to cart successfully",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                    homeViewModel.updateClothe(cloth.number, cloth.id)
                }
            }
            binding.remove.setOnClickListener(){
                if(binding.want.text.toString().toInt()<=1)
                {
                    Toast.makeText(activity,"Invalid number", Toast.LENGTH_LONG).show()
                }else
                {
                    binding.want.text = (binding.want.text.toString().toInt()-1).toString()
                    cloth.number=cloth.number+1
                    var t = binding.want.text.toString().toInt() * cloth.price
                    viewModel.updateCart(binding.want.text.toString().toInt(), t, cloth.id)
                    FancyToast.makeText(
                        activity,
                        "item has been updated to cart successfully",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                    homeViewModel.updateClothe(cloth.number, cloth.id)
                }
            }
            binding.executePendingBindings()
        }
    }

    class CartDiffCallBack : DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

    }
//
//    class CartListenerClass(val clickListener: (cart:Cart) -> Unit) {
//        fun onClick(cart:Cart) = clickListener(cart)
//    }
}