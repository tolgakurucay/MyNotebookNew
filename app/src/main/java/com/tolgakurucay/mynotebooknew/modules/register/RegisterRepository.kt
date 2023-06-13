package com.tolgakurucay.mynotebooknew.modules.register

import com.tolgakurucay.mynotebooknew.base.BaseRepository
import com.tolgakurucay.mynotebooknew.services.ApiServices
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import com.tolgakurucay.mynotebooknew.services.register.RegisterRequest
import javax.inject.Inject

class RegisterRepository @Inject constructor(myNotebookNewService: MyNotebookNewService): BaseRepository(myNotebookNewService) {

    suspend fun register(request: RegisterRequest) = request(ApiServices.Register(request))
}