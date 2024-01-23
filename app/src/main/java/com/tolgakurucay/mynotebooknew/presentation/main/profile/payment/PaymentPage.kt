package com.tolgakurucay.mynotebooknew.presentation.main.profile.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R

@Composable
@Preview
fun PaymentPage(onPaymentTypeSelected: (PaymentType) -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPaymentTypeSelected.invoke(PaymentType.BASIC_BUCKET) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.basic_plan),
                    contentDescription = stringResource(id = R.string.title_basic_bucket),
                    modifier = Modifier.size(60.dp)

                )
                Text(
                    text = stringResource(id = R.string.title_basic_bucket),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = stringResource(id = R.string.right_20))
                Text(text = "Price")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPaymentTypeSelected.invoke(PaymentType.ADVANTAGE_BUCKET) },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.advantage_plan),
                    contentDescription = stringResource(id = R.string.title_advantage_bucket),
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = stringResource(id = R.string.title_advantage_bucket),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = stringResource(id = R.string.right_50))
                Text(text = "Price")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPaymentTypeSelected.invoke(PaymentType.PREMIUM_BUCKET) },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.premium_plan),
                    contentDescription = stringResource(id = R.string.title_premium_bucket),
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = stringResource(id = R.string.title_premium_bucket),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = stringResource(id = R.string.right_100))
                Text(text = "Price")
            }

        }
    }
}

enum class PaymentType {
    BASIC_BUCKET,
    ADVANTAGE_BUCKET,
    PREMIUM_BUCKET
}