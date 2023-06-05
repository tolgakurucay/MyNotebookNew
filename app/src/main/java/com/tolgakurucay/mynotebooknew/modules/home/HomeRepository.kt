package com.tolgakurucay.mynotebooknew.modules.home

import com.tolgakurucay.mynotebooknew.base.BaseRepository
import com.tolgakurucay.mynotebooknew.services.ApiServices
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

class HomeRepository @Inject constructor(myNotebookNewService: MyNotebookNewService): BaseRepository(myNotebookNewService) {

    suspend fun getJsonFromTest(page : Int) = request(ApiServices.MainContent(page))
}