package com.example.myapplication.fragment

import android.os.Bundle
import android.text.BoringLayout.make
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Adapter.GetFootballAdapter
import com.example.myapplication.Adapter.GetUserAdapter
import com.example.myapplication.Adapter.WishListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.model.ClassA
import com.example.myapplication.model.ClassB
import com.example.myapplication.model.ClassC
import com.example.myapplication.model.Photo
import com.example.myapplication.network.RequestResult
import com.example.myapplication.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar.make
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class Fragment1 : Fragment(),ClickFragment1 {
    var str : String = "log info"
    lateinit var binding: Fragment1Binding
    private val viewModel: UserViewModel by activityViewModels()
    lateinit var adapter:GetFootballAdapter

    lateinit var adapteruser:GetUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_1, container, false
        )
        iniUI()
        return binding.root
    }
    fun iniUI(){
        binding.click = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.textObserver = viewModel.textObservable
//        getUserDetails(viewModel.selectedsport,viewModel.seleteddate)
        binding.tvdate.setStartDate(7,6,2022)
        binding.tvdate.getSelectedDate {
            viewModel.seleteddate = SimpleDateFormat("yyyyMMdd").format(it)
            if(viewModel.selectedsport == "football"){
                Log.e("SHIFA1", "iniUI: true", )
                binding.rv1Items.post { // Call smooth scroll
                    binding.rv1Items.scrollToPosition(0)
                }
                getUserDetailsff(viewModel.selectedsport,viewModel.seleteddate)
            }else{
                binding.rvItems.post { // Call smooth scroll
                    binding.rvItems.scrollToPosition(0)
                }
                getUserDetails(viewModel.selectedsport,viewModel.seleteddate)
            }

        }

        iniRV()

        if(viewModel.selectedsport == "football"){
            Log.e("SHIFA1", "iniUI: true", )
            getUserDetailsff(viewModel.selectedsport,viewModel.seleteddate)
        }else{
            getUserDetails(viewModel.selectedsport,viewModel.seleteddate)
        }

    }
    fun iniRV(){
        adapter = GetFootballAdapter(requireActivity())
        binding.rv1Items.adapter = adapter

        adapteruser = GetUserAdapter(requireActivity())
        binding.rvItems.adapter = adapteruser
    }

    override fun getUserDetails(sports:String,dateselect:String){
            viewModel.getValues(sports,dateselect).observe(viewLifecycleOwner,{
                it?.let {
                    when(it.status){
                        RequestResult.Status.SUCCESS->{
                            viewModel.loading.value = false
                            viewModel.text.value = "Sucess"
                            it.data?.let {
                                    adapteruser.setDataChanged(it.stages)
                            }

//                            viewModel.textObservable.setValue(requireContext(),it.data)
//                            Toast.makeText(requireContext(),it.data,)
//                            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
                        }
                        RequestResult.Status.ERROR ->{
                            viewModel.loading.value = false
                            viewModel.text.value = "Error"
                            viewModel.textObservable.setValue("Error")
                        }
                        RequestResult.Status.LOADING->{
                            viewModel.loading.value = true
                        }
                    }
                }
            })

    }
     fun getUserDetailsff(sports:String,dateselect:String){
        viewModel.getValues(sports,dateselect).observe(viewLifecycleOwner,{
            it?.let {
                Log.e("SHIFA1", "getUserDetailsff: ${it.status}", )
                when(it.status){
                    RequestResult.Status.SUCCESS->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Sucess"
                        it.data?.let {
                            Log.e("SHIFA1", "getUserDetailsff: heloooo", )
                            Log.e("SHIFA1", "getUserDetailsff: ${viewModel.selectedsport}", )
                                adapter.setDataChanged(it.stages)

                        }

//                            viewModel.textObservable.setValue(requireContext(),it.data)
//                            Toast.makeText(requireContext(),it.data,)
//                            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
                    }
                    RequestResult.Status.ERROR ->{
                        viewModel.loading.value = false
                        viewModel.text.value = "Error"
                        viewModel.textObservable.setValue("Error")
                    }
                    RequestResult.Status.LOADING->{
                        viewModel.loading.value = true
                    }
                }
            }
        })

    }
    override fun getTodo(){
      viewModel.selectedsport = "football"
        viewModel.selectedsportmodel?.sportselected = "football"
        binding.rv1Items.post { // Call smooth scroll
            binding.rv1Items.scrollToPosition(0)
        }
         binding.rvItems.visibility = View.GONE
        binding.rv1Items.visibility = View.VISIBLE
        getUserDetailsff( viewModel.selectedsport,viewModel.seleteddate)

    }

    override fun getAlbum(){
        viewModel.selectedsport = "cricket"
        viewModel.selectedsportmodel?.sportselected = "cricket"
        binding.rvItems.post { // Call smooth scroll
            binding.rvItems.scrollToPosition(0)
        }
        binding.rvItems.visibility = View.VISIBLE
        binding.rv1Items.visibility = View.GONE
        getUserDetails( viewModel.selectedsport,viewModel.seleteddate)

    }


    override fun set() {
//        viewModel.textObservable.setValue("demo")
    }

    override fun get() {
//        Toast.makeText(requireContext(),viewModel.textObservable.getValue(),Toast.LENGTH_SHORT).show()
    }

    override fun getPhoto() {

    }

    override fun onClickGotoFragment2() {
//        findNavController().navigate(R.id.actionFragment1TOFragment2)
    }

}