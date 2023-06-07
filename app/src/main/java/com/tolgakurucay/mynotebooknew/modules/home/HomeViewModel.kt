package com.tolgakurucay.mynotebooknew.modules.home

import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.extensions.callService
import com.tolgakurucay.mynotebooknew.services.TestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository):
    BaseViewModel() {


    val testResponse = MutableStateFlow<TestResponse?>(null)


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


