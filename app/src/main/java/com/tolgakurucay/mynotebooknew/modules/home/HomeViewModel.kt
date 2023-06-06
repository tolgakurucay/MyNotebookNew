package com.tolgakurucay.mynotebooknew.modules.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.extensions.callService
import com.tolgakurucay.mynotebooknew.services.TestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository):
    BaseViewModel() {


    val testResponse = mutableStateOf<TestResponse?>(null)



    fun getMainContent(page : Int) {
        viewModelScope.callService(
            vm = this,
            success = {
                testResponse.value = it
            },
            service = {
               homeRepository.getJsonFromTest(page)
            },
        )
    }


}


