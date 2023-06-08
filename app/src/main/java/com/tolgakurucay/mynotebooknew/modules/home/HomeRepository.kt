package com.tolgakurucay.mynotebooknew.modules.home

import com.tolgakurucay.mynotebooknew.base.BaseRepository
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import javax.inject.Inject

class HomeRepository @Inject constructor(myNotebookNewService: MyNotebookNewService): BaseRepository(myNotebookNewService) {

}