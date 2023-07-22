package com.tolgakurucay.mynotebooknew.domain.base

import com.tolgakurucay.mynotebooknew.util.isNull


fun validateCustomTextFields(array: Array<String?>): Boolean {
    var isValidated = true
    array.forEach { field ->
        if (field.isNull()) isValidated = false

    }
    return isValidated
}


fun arePasswordsSame(password: String?, passwordAgain: String?): Boolean {

    return password == passwordAgain
}

