package com.tolgakurucay.mynotebooknew.custom

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.tolgakurucay.mynotebooknew.R


@Composable
fun CustomTextField(
    textFieldType: TextFieldType,
    horizontalMargin: Dp,
    value: String,
    onValueChange: (newValue: String) -> Unit
) {
    //Validation is true when supportingText field is empty ""
    //Validation is false when supportingText field isn't empty "example text"
    var supportingText by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

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
                TextFieldType.PASSWORD_AGAIN -> KeyboardType.Password
                TextFieldType.NAME -> KeyboardType.Text
                TextFieldType.SURNAME -> KeyboardType.Text
            },
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            when (textFieldType) {
                TextFieldType.EMAIL -> {
                    //Checking validation
                    if (value.isEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.twotone_email_24),
                            contentDescription = stringResource(
                                id = R.string.cd_email
                            )
                        )
                    } else {
                        when (supportingText) {
                            "" ->
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )

                            else -> Image(
                                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                                contentDescription = stringResource(
                                    id = R.string.cd_false
                                )
                            )
                        }
                    }

                }

                TextFieldType.PASSWORD -> {
                    if (value.isEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.twotone_password_24),
                            contentDescription = stringResource(
                                id = R.string.cd_password
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" ->
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )

                            else -> Image(
                                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                                contentDescription = stringResource(
                                    id = R.string.cd_false
                                )
                            )
                        }
                    }

                }
                TextFieldType.PASSWORD_AGAIN -> {
                    if (value.isEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.twotone_password_24),
                            contentDescription = stringResource(
                                id = R.string.cd_password_again
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" ->
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )

                            else -> Image(
                                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                                contentDescription = stringResource(
                                    id = R.string.cd_false
                                )
                            )
                        }
                    }

                }

                TextFieldType.NAME -> {
                    if (value.isEmpty()) {
                        Image(
                            imageVector = Icons.Filled.Person,
                            contentDescription = stringResource(
                                id = R.string.cd_name_icon
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" ->
                                Image(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = stringResource(
                                        id = R.string.cd_person
                                    )
                                )

                            else -> Image(
                                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                                contentDescription = stringResource(
                                    id = R.string.cd_false
                                )
                            )
                        }
                    }

                }

                TextFieldType.SURNAME -> {
                    if (value.isEmpty()) {
                        Image(
                            imageVector = Icons.Filled.AccountTree,
                            contentDescription = stringResource(
                                id = R.string.cd_person_tree
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" ->
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )

                            else -> Image(
                                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                                contentDescription = stringResource(
                                    id = R.string.cd_false
                                )
                            )
                        }
                    }

                }
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalMargin),
        supportingText = {
            //Validation rules
            when (textFieldType) {
                TextFieldType.EMAIL -> {
                    supportingText = if (value.isEmpty()) {
                        stringResource(id = R.string.empty_email)
                    } else if (!EMAIL_ADDRESS.matcher(value).matches()) {
                        stringResource(id = R.string.action_enter_valid_email)
                    } else {
                        ""
                    }
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.PASSWORD -> {
                    supportingText = if (value.isEmpty()) {
                        stringResource(id = R.string.empty_password)
                    } else {
                        ""
                    }
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )

                }
                TextFieldType.PASSWORD_AGAIN -> {
                    supportingText = if (value.isEmpty()) {
                        stringResource(id = R.string.empty_password_again)
                    } else {
                        ""
                    }
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )

                }

                TextFieldType.NAME -> {
                    supportingText = if (value.isEmpty()) {
                        stringResource(id = R.string.empty_name)
                    } else {
                        ""
                    }
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.SURNAME -> {
                    supportingText = if (value.isEmpty()) {
                        stringResource(id = R.string.empty_surname)
                    } else {
                        ""
                    }
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

            }

        },
        placeholder = {
            when (textFieldType) {
                TextFieldType.EMAIL -> Text(
                    text = stringResource(id = R.string.action_enter_email),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextFieldType.PASSWORD -> Text(
                    text = stringResource(id = R.string.action_enter_password),
                    style = MaterialTheme.typography.bodyMedium
                )
                TextFieldType.PASSWORD_AGAIN -> Text(
                    text = stringResource(id = R.string.action_enter_password_again),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextFieldType.NAME -> Text(
                    text = stringResource(id = R.string.action_enter_name),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextFieldType.SURNAME -> Text(
                    text = stringResource(id = R.string.action_enter_surname),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        },
        shape = MaterialTheme.shapes.medium,
        keyboardActions = KeyboardActions(
            onGo = {
                focusManager.clearFocus()
            },
        ),
        isError = if (value.isEmpty()) {
            false
        } else {
            when (supportingText) {
                "" -> false
                else -> true
            }
        },
        visualTransformation = when (textFieldType) {
            TextFieldType.PASSWORD -> {
                if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None
            }

            else -> VisualTransformation.None
        }, trailingIcon = {
            if (textFieldType == TextFieldType.PASSWORD) {
                when (showPassword) {
                    true -> {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = stringResource(
                                    id = R.string.common_hide_password
                                ),
                            )
                        }
                    }

                    false -> {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(
                                    id = R.string.common_show_password
                                ),
                            )
                        }
                    }
                }

            }
        }

    )

}