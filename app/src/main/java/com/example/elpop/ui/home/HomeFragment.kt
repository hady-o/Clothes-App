package com.example.elpop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.elpop.R
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.Clothe
import com.example.elpop.databinding.FragmentHomeBinding
import com.example.elpop.ui.home.history.HistoryFragment
import com.example.elpop.ui.home.person.PersonFragment
import com.example.elpop.ui.home.report.ReportsFragment
import com.example.elpop.ui.home.userhome.UserHomeFragment
import com.google.android.gms.common.api.internal.LifecycleCallback.getFragment
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        getFragment(UserHomeFragment())

        val args = arguments?.let { com.example.elpop.ui.home.HomeFragmentArgs.fromBundle(it) }
        args?.let {
            if (it.from==1){
                getFragment(HistoryFragment())
            }else if(it.from==2||it.from==3){
                getFragment(PersonFragment())
            }else if(it.from==4){
                getFragment(ReportsFragment())
            }else{
                getFragment(UserHomeFragment())
            }
        }
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.navBarId.setOnItemSelectedListener() {

            if (it.itemId == R.id.homeMenuId) {
                getFragment(UserHomeFragment())
            } else if (it.itemId == R.id.history) {
                getFragment(HistoryFragment())
            } else if (it.itemId == R.id.personMenuId) {
                getFragment(PersonFragment())
            } else if (it.itemId == R.id.importExport) {
                getFragment(ReportsFragment())
            }
            true
        }


    binding.addBtn.setOnClickListener(){
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_addClotheFragment)
    }

        return binding.root
    }
    private fun getFragment(dest: Fragment) {
        var frag = parentFragmentManager
        var tran = frag.beginTransaction()
        tran.replace(R.id.frameId, dest)
        tran.commit()
    }
    fun addCloth(clothe: Clothe) {
        FirebaseFirestore.getInstance().collection("clothes")
            .add(clothe)
    }

}