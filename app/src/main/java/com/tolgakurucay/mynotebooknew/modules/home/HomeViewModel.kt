package com.tolgakurucay.mynotebooknew.modules.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.extensions.callService
import com.tolgakurucay.mynotebooknew.services.TestResponse
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {


    val testResponse = MutableLiveData<TestResponse>()

    fun getMainContent() {
        viewModelScope.callService(
            vm = this,
            success = {
                testResponse.value = it
            },
            service = {
                homeRepository.getJsonFromTest()
            },
        )
    }


}


