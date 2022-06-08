package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.databinding.Fragment2Binding
import com.example.myapplication.network.RequestResult
import com.example.myapplication.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment2 : Fragment(), CLickFragment2 {
    var str : String = "log info"
    private val viewModel: UserViewModel by activityViewModels()
    lateinit var binding: Fragment2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_2, container, false
        )
        iniUI()
        return binding.root
    }
    fun iniUI(){
        binding.click = this
    }

   override fun onClickGotoFragment1() {
        findNavController().navigateUp()
    }
}