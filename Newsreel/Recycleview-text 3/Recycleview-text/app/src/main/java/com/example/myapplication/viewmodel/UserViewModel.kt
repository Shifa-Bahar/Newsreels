package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.model.sports.Stages
import com.example.myapplication.network.performNwOperation
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.repository.UserRepository2
import com.example.myapplication.utils.InputEditTextValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    val repository: UserRepository,
    val repository2: UserRepository2,
) : BaseViewModel(application) {

    var text = MutableLiveData<String>()
    var seleteddate : String = "20220607"
    var seleteddatevalue : Boolean = true
    var selectedsport : String = "cricket"
    var selectedsportmodel: Stages? = null
//    var selectedsportmodel : Stages =
    var textObservable: InputEditTextValidator =
        InputEditTextValidator(
            InputEditTextValidator.InputEditTextValidationsEnum.ID,
            true,
            null,
            null
        )
    var loading = MutableLiveData<Boolean>()

    fun getValues(category: String,date:String) =
        performNwOperation { repository.getValue(category,
            date) }

    fun getTodo() =
        performNwOperation { repository.getTodo() }
    fun getAlbum() =
        performNwOperation { repository2.getAlbum() }
    fun getPhoto() =
        performNwOperation { repository.getPhoto() }

    //    var api = RetrofitClient.getApi()
//    var repository  = api.let { UserRepository(it) }
}