package com.tolgakurucay.mynotebooknew

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomTextField(
    textFieldType: TextFieldType,
    value: String,
    onValueChange: (newValue: String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange.invoke(it)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType =
            when (textFieldType) {
                TextFieldType.EMAIL -> KeyboardType.Email
                TextFieldType.PASSWORD -> KeyboardType.Password
            },
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            when (textFieldType) {
                TextFieldType.EMAIL -> Image(
                    painter = painterResource(id = R.drawable.twotone_email_24),
                    contentDescription = stringResource(
                        id = R.string.description_email
                    ),
                )

                TextFieldType.PASSWORD -> Image(
                    painter = painterResource(id = R.drawable.twotone_password_24),
                    contentDescription = stringResource(
                        id = R.string.description_password
                    ),
                )
            }

        },
        modifier = Modifier.fillMaxWidth(),
        supportingText = {

        },
    )

}