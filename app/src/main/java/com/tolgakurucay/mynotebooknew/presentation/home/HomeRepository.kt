package com.tolgakurucay.mynotebooknew.presentation.home

import com.tolgakurucay.mynotebooknew.domain.base.BaseRepository
import com.tolgakurucay.mynotebooknew.domain.model.MyNotebookNewService
import javax.inject.Inject

class HomeRepository @Inject constructor(myNotebookNewService: MyNotebookNewService): BaseRepository(myNotebookNewService) {

}