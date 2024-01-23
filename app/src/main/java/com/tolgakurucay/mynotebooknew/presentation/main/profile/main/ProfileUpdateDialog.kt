package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomLoading
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.util.isNull
import com.tolgakurucay.mynotebooknew.util.makeSmallerBitmap
import com.tolgakurucay.mynotebooknew.util.toBase64
import com.tolgakurucay.mynotebooknew.util.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview
@Composable
fun ProfileUpdateDialog(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    profileResponse: ProfileResponse = ProfileResponse(),
    onCancelClicked: () -> Unit = {},
    onUpdate: (ProfileResponse) -> Unit = {}

) {
    var name by remember { mutableStateOf(profileResponse.name) }
    var surname by remember { mutableStateOf(profileResponse.surname) }
    var loading by remember { mutableStateOf(false) }
    val imageUri = remember { mutableStateOf<Uri?>(null) }


    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }


    Dialog(onDismissRequest = { onCancelClicked.invoke() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (loading) {
                    CustomLoading()
                } else {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.action_update_profile),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .clickable { onCancelClicked.invoke() }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        CustomTextField(textFieldType = TextFieldType.NAME, onValueChange = {
                            name = it
                        }, defaultValue = name.orEmpty())
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomTextField(textFieldType = TextFieldType.SURNAME, onValueChange = {
                            surname = it
                        }, defaultValue = surname.orEmpty())
                        Spacer(modifier = Modifier.height(8.dp))
                        ExtendedFloatingActionButton(
                            onClick = {
                                launcher.launch("image/*")
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            contentColor = if (imageUri.value.isNull()) Color.Blue else Color.Green

                        ) {
                            Icon(
                                imageVector = if (imageUri.value.isNull()) Icons.Filled.CameraEnhance else Icons.Filled.Verified,
                                contentDescription = stringResource(
                                    id = R.string.action_add_photo
                                ),
                            )
                            Text(
                                text = stringResource(
                                    id = if (imageUri.value.isNull()) R.string.action_add_photo else R.string.photo_added
                                ), modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                            Button(
                                onClick = {
                                    if (arrayOf(name, surname).validateCustomTextFields()) {
                                        loading = true
                                        coroutineScope.launch {
                                            val image =
                                                imageUri.value.toBitmap(context).toBase64(10)
                                            withContext(Dispatchers.Main) {
                                                loading = false
                                                onUpdate.invoke(
                                                    ProfileResponse(
                                                        name = name,
                                                        surname = surname,
                                                        photo = image
                                                    )
                                                )
                                                onCancelClicked.invoke()
                                            }
                                        }

                                    }
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(text = stringResource(id = R.string.action_update))
                            }
                        }


                    }
                }

            }
        }
    }
}
