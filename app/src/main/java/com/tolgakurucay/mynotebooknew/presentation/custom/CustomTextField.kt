package com.tolgakurucay.mynotebooknew.presentation.custom

import android.content.Context
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Title
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing0


@Preview
@Composable
fun CustomTextField(
    textFieldType: TextFieldType = TextFieldType.EMAIL,
    horizontalMargin: Dp = spacing0,
    defaultValue: String = "",
    onValueChange: (newValue: String?) -> Unit = {}
) {

    var supportingText by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(defaultValue) }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = when (textFieldType) {
            TextFieldType.EMAIL -> value.lowercase()
            else -> value
        },
        onValueChange = {
            value = it
            //If validated the textField, sending its string. Else sending null
            if (isTextFieldValidated(textFieldType, it)) {
                onValueChange.invoke(it)
            } else {
                onValueChange.invoke(null)
            }


        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType =
            when (textFieldType) {
                TextFieldType.EMAIL -> KeyboardType.Email
                TextFieldType.PASSWORD -> KeyboardType.Password
                TextFieldType.PASSWORD_AGAIN -> KeyboardType.Password
                TextFieldType.NAME -> KeyboardType.Text
                TextFieldType.SURNAME -> KeyboardType.Text
                TextFieldType.TITLE -> KeyboardType.Text
                TextFieldType.DESCRIPTION -> KeyboardType.Text
            },
        ),
        minLines = if (textFieldType == TextFieldType.DESCRIPTION) 20 else 1,
        singleLine = when (textFieldType) {
            TextFieldType.TITLE -> false
            TextFieldType.DESCRIPTION -> false
            else -> true
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            when (textFieldType) {
                TextFieldType.EMAIL -> {
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

                TextFieldType.TITLE -> {
                    if (value.isEmpty()) {
                        Image(
                            imageVector = Icons.Filled.Title,
                            contentDescription = stringResource(
                                id = R.string.cd_title
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" -> {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )
                            }

                            else -> {
                                Image(
                                    painter =
                                    painterResource(id = R.drawable.baseline_error_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_false
                                    )
                                )
                            }
                        }
                    }
                }

                TextFieldType.DESCRIPTION -> {
                    if (value.isEmpty()) {
                        Image(
                            imageVector = Icons.Filled.Description,
                            contentDescription = stringResource(
                                id = R.string.cd_description
                            ),
                        )
                    } else {
                        when (supportingText) {
                            "" -> {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_true
                                    ),
                                )
                            }

                            else -> {
                                Image(
                                    painter =
                                    painterResource(id = R.drawable.baseline_error_outline_24),
                                    contentDescription = stringResource(
                                        id = R.string.cd_false
                                    )
                                )
                            }
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
            supportingText = supportingMessage(textFieldType, value, LocalContext.current)

            when (textFieldType) {

                TextFieldType.EMAIL -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.PASSWORD -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )

                }

                TextFieldType.PASSWORD_AGAIN -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )

                }

                TextFieldType.NAME -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.SURNAME -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.TITLE -> {
                    Text(
                        text = supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red
                    )
                }

                TextFieldType.DESCRIPTION -> {
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

                TextFieldType.TITLE -> Text(
                    text = stringResource(id = R.string.action_enter_title),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextFieldType.DESCRIPTION -> Text(
                    text = stringResource(id = R.string.action_enter_description),
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

            TextFieldType.PASSWORD_AGAIN -> {
                if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None
            }

            else -> VisualTransformation.None
        },
        trailingIcon = {
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

            } else if (textFieldType == TextFieldType.PASSWORD_AGAIN) {
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
        },

        )

}


enum class TextFieldType {
    EMAIL,
    PASSWORD,
    NAME,
    SURNAME,
    PASSWORD_AGAIN,
    TITLE,
    DESCRIPTION
}

private fun isTextFieldValidated(type: TextFieldType, value: String): Boolean {
    return when (type) {
        TextFieldType.NAME -> {
            value.isNotEmpty()
        }

        TextFieldType.SURNAME -> {
            value.isNotEmpty()
        }

        TextFieldType.EMAIL -> {
            if (value.isEmpty()) {
                false
            } else EMAIL_ADDRESS.matcher(value).matches()
        }

        TextFieldType.PASSWORD -> {
            value.isNotEmpty()
        }

        TextFieldType.PASSWORD_AGAIN -> {
            value.isNotEmpty()
        }

        TextFieldType.TITLE -> {
            value.isNotEmpty()
        }

        TextFieldType.DESCRIPTION -> {
            value.isNotEmpty()
        }
    }

}

private fun supportingMessage(type: TextFieldType, value: String, context: Context): String {
    return when (type) {
        TextFieldType.EMAIL -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_email)
            } else if (!EMAIL_ADDRESS.matcher(value).matches()) {
                context.getString(R.string.action_enter_valid_email)
            } else {
                ""
            }

        }

        TextFieldType.PASSWORD -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_password)
            } else {
                ""
            }


        }

        TextFieldType.PASSWORD_AGAIN -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_password_again)
            } else {
                ""
            }


        }

        TextFieldType.NAME -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_name)
            } else {
                ""
            }

        }

        TextFieldType.SURNAME -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_surname)
            } else {
                ""
            }

        }

        TextFieldType.TITLE -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_title)
            } else {
                ""
            }
        }

        TextFieldType.DESCRIPTION -> {
            if (value.isEmpty()) {
                context.getString(R.string.empty_description)
            } else {
                ""
            }
        }
    }
}