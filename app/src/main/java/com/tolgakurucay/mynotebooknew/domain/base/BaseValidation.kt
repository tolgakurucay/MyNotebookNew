package com.tolgakurucay.mynotebooknew.domain.base

import com.tolgakurucay.mynotebooknew.util.isNull


fun Array<String?>.validateCustomTextFields(): Boolean {
    var isValidated = true
    forEach { field ->
        if (field.isNull()) isValidated = false

    }
    return isValidated
}


fun arePasswordsSame(password: String?, passwordAgain: String?): Boolean {

    return password == passwordAgain
}

