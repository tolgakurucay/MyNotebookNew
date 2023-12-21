package com.tolgakurucay.mynotebooknew.presentation.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing0


@Preview
@Composable
fun CustomButton(
    buttonType: ButtonType = ButtonType.DEFAULT,
    buttonSize: ButtonSize = ButtonSize.MATCH_PARENT,
    horizontalMargin: Dp = spacing0,
    onClick: () -> Unit = {}
) {

    val modifier = when (buttonSize) {
        ButtonSize.MATCH_PARENT -> {
            Modifier
                .padding(horizontal = horizontalMargin)
                .fillMaxWidth()
        }

        ButtonSize.WRAP_CONTENT -> {
            Modifier
                .padding(horizontal = horizontalMargin)
                .wrapContentWidth()
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(
                id = when (buttonType) {
                    ButtonType.ADD_NOTE -> R.string.common_add_note_uppercase
                    ButtonType.LOGIN -> R.string.common_login_uppercase
                    ButtonType.REGISTER -> R.string.common_register_uppercase
                    ButtonType.FORGOT_PASSWORD -> R.string.common_forgot_password_uppercase
                    ButtonType.UPDATE_NOTE -> R.string.common_update_note_uppercase
                    ButtonType.OK -> R.string.common_okay
                    ButtonType.CANCEL -> R.string.action_cancel
                    ButtonType.UPDATE -> R.string.action_update
                    else -> R.string.common_space
                },
            ), style = MaterialTheme.typography.labelMedium
        )

    }

}


enum class ButtonType {
    DEFAULT,
    REGISTER,
    LOGIN,
    FORGOT_PASSWORD,
    ADD_NOTE,
    UPDATE_NOTE,
    OK,
    CANCEL,
    UPDATE
}

enum class ButtonSize {
    MATCH_PARENT,
    WRAP_CONTENT
}





